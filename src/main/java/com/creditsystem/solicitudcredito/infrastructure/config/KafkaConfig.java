package com.creditsystem.solicitudcredito.infrastructure.config;

import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id:solicitud-credito-group}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset:earliest}")
    private String autoOffsetReset;

    @Value("${spring.kafka.producer.key-serializer:org.apache.kafka.common.serialization.StringSerializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer:org.springframework.kafka.support.serializer.JsonSerializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks:all}")
    private String acks;

    @Value("${spring.kafka.producer.retries:3}")
    private int retries;

    @Value("${spring.kafka.producer.delivery-timeout-seconds:30}")
    private int deliveryTimeoutSeconds;

    @Value("${spring.kafka.topic.notifications:solicitud-credito-notificaciones}")
    private String notificationsTopic;

    @Value("${spring.kafka.topic.notifications-partitions:3}")
    private int notificationsPartitions;

    @Value("${spring.kafka.topic.notifications-replication-factor:1}")
    private int notificationsReplicationFactor;

    @Bean
    public NewTopic notificationsTopic() {
        log.info("Creando tema de Kafka: {} con {} particiones y factor de replicación {}",
                notificationsTopic, notificationsPartitions, notificationsReplicationFactor);
        return TopicBuilder.name(notificationsTopic)
                .partitions(notificationsPartitions)
                .replicas(notificationsReplicationFactor)
                .build();
    }

    @Bean
    public ProducerFactory<String, SolicitudCreditoEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutSeconds * 1000);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(ProducerConfig.TRANSACTION_TIMEOUT_MS_CONFIG, deliveryTimeoutSeconds * 1000);

        log.debug("Configuración del ProducerFactory: bootstrapServers={}, acks={}, retries={}",
                bootstrapServers, acks, retries);

        DefaultKafkaProducerFactory<String, SolicitudCreditoEvent> factory =
                new DefaultKafkaProducerFactory<>(configProps);
        factory.setTransactionIdPrefix("solicitud-credito-tx-");
        return factory;
    }

    @Bean
    public KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate() {
        KafkaTemplate<String, SolicitudCreditoEvent> template = new KafkaTemplate<>(producerFactory());
        template.setTransactionIdPrefix("solicitud-credito-tx-");
        log.info("KafkaTemplate creado exitosamente");
        return template;
    }

    @Bean
    public ConsumerFactory<String, SolicitudCreditoEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.creditsystem.solicitudcredito.infrastructure.events");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, SolicitudCreditoEvent.class.getName());

        log.debug("Configuración del ConsumerFactory: bootstrapServers={}, groupId={}, autoOffsetReset={}",
                bootstrapServers, groupId, autoOffsetReset);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SolicitudCreditoEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, SolicitudCreditoEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, SolicitudCreditoEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        factory.getContainerProperties().setAckMode(org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate()),
                new FixedBackOff(1000L, 3)
        );
        factory.setCommonErrorHandler(errorHandler);

        log.info("ConcurrentKafkaListenerContainerFactory configurado con concurrencia 3");
        return factory;
    }

    @Bean
    public KafkaTransactionManager<String, SolicitudCreditoEvent> transactionManager(
            ProducerFactory<String, SolicitudCreditoEvent> producerFactory) {
        KafkaTransactionManager<String, SolicitudCreditoEvent> manager =
                new KafkaTransactionManager<>(producerFactory);
        log.info("KafkaTransactionManager configurado");
        return manager;
    }
}