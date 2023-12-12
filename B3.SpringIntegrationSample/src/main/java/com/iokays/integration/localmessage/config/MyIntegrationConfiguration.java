package com.iokays.integration.localmessage.config;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.iokays.integration.localmessage.event.DomainEvent;
import com.iokays.integration.localmessage.event.StoredEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.messaging.MessageHandler;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Integration configuration.
 */
@Configuration
@EnableIntegration
public class MyIntegrationConfiguration {

    @Bean
    public IntegrationFlow eventsFlow(final MessageProducerSupport messageProducer,
                                      final MessageHandler jdbcMessageHandler) {
        return IntegrationFlow.from(messageProducer)
                .handle(jdbcMessageHandler)
                .get();
    }

    /**
     * 监听事件
     * @return
     */
    @Bean
    public MessageProducerSupport messageProducer() {
        final var producer = new ApplicationEventListeningMessageProducer();
        producer.setEventTypes(DomainEvent.class);
        return producer;
    }

    /**
     * 消息处理器, 将事件写入数据库
     * @param dataSource
     * @param gson
     * @return
     */
    @Bean
    public MessageHandler jdbcMessageHandler(final DataSource dataSource,
                                             final Gson gson) {
        final var updateSql = "insert into tbl_stored_event (event_body, occurred_on, type_name, create_time) values (?, ?, ?, ?)";
        final var handler = new JdbcMessageHandler(dataSource, updateSql);
        handler.setPreparedStatementSetter((ps, message) -> {
            final var event = Objects.requireNonNull((DomainEvent) message.getPayload());
            ps.setString(1, gson.toJson(event));
            ps.setObject(2, Objects.requireNonNull(event.getOccurredOn()));
            ps.setString(3, event.getClass().getName());
            ps.setObject(4, LocalDateTime.now());
        });
        return handler;
    }

    /**
     * 自定义 Gson
     * @return
     */
    @Bean
    public Gson gson() {
        final DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        final JsonSerializer<LocalDateTime> localDateTimeJsonSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(isoLocalDateTime.format(src));
        final JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer = (json, typeOfT, context) -> isoLocalDateTime.parse(json.getAsString(), LocalDateTime::from);

        return new Gson().newBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonSerializer)
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonDeserializer)
                .create();
    }

    /**
     * 循环读取事件
     * @param jdbcPollingChannelAdapter
     * @return
     */
    @Bean
    public IntegrationFlow pollingFlow(final JdbcPollingChannelAdapter jdbcPollingChannelAdapter) {
        return IntegrationFlow.from(jdbcPollingChannelAdapter, c -> c.poller(Pollers.fixedRate(100).transactional()))
                .handle(v -> {
                    final var storedEvent = (List<StoredEvent>) v.getPayload();
                    storedEvent.forEach(System.out::println);
                    System.out.println("=================写入MQ===================");
                })
                .get();
    }


    /**
     * 从数据库读取事件
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcPollingChannelAdapter jdbcPollingChannelAdapter(final DataSource dataSource) {
        final var selectQuery = "select * from tbl_stored_event where event_id > (select ifnull(max(event_id), 0) from tbl_dispatcher_last_event) order by event_id asc";
        final var adapter = new JdbcPollingChannelAdapter(dataSource, selectQuery);

        adapter.setRowMapper((rs, rowNum) -> {
            final var eventId = rs.getLong("event_id");
            final var eventBody = rs.getString("event_body");
            final var occurredOn = rs.getObject("occurred_on", LocalDateTime.class);
            final var typeName = rs.getString("type_name");
            final var createTime = rs.getObject("create_time", LocalDateTime.class);
            return new StoredEvent(eventId, eventBody, occurredOn, typeName, createTime);
        });

        adapter.setUpdateSql("update tbl_dispatcher_last_event set event_id = :event_id");
        adapter.setUpdateSqlParameterSourceFactory(((input) -> {
            final var storedEvent = (List<StoredEvent>) input;
            return new MapSqlParameterSource().addValue("event_id", storedEvent.get(storedEvent.size() -1).eventId());
        }));

        return adapter;
    }



}
