package com.iokays.sample.config;

import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jdbc.channel.PostgresChannelMessageTableSubscriber;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;
import org.springframework.integration.jdbc.store.channel.PostgresChannelMessageStoreQueryProvider;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableIntegration
public class MessageConfig {

    @Bean
    public JdbcChannelMessageStore messageStore(DataSource dataSource) {
        JdbcChannelMessageStore messageStore = new JdbcChannelMessageStore(dataSource);
        messageStore.setChannelMessageStoreQueryProvider(new PostgresChannelMessageStoreQueryProvider());
        return messageStore;
    }

    @Bean
    public QueueChannel queueChannel(JdbcChannelMessageStore messageStore) {
        return new PriorityChannel(messageStore, "some group");
    }

    @Bean
    public PostgresChannelMessageTableSubscriber subscriber(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        return new PostgresChannelMessageTableSubscriber(() ->
                DriverManager.getConnection(url, username, password).unwrap(PgConnection.class));
    }

    @Bean
    public PostgresSubscribableChannel channel(
            PostgresChannelMessageTableSubscriber subscriber,
            JdbcChannelMessageStore messageStore) {
        return new PostgresSubscribableChannel(messageStore, "some group", subscriber);
    }

}

