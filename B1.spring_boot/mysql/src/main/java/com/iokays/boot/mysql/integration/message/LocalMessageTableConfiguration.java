package com.iokays.boot.mysql.integration.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.jdbc.store.JdbcMessageStore;
import org.springframework.integration.jdbc.store.channel.ChannelMessageStorePreparedStatementSetter;
import org.springframework.integration.jdbc.store.channel.MySqlChannelMessageStoreQueryProvider;
import org.springframework.integration.jdbc.store.channel.PostgresChannelMessageStoreQueryProvider;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import javax.sql.DataSource;

/**
 *  本地消息表
 */
@Configuration
public class LocalMessageTableConfiguration {

    @Bean
    public JdbcMessageStore jdbcMessageStore(DataSource dataSource) {
        final var jdbcMessageStore = new JdbcMessageStore(dataSource);
        jdbcMessageStore.setTablePrefix("local_");
        return jdbcMessageStore;
    }

    @Bean
    public MySqlChannelMessageStoreQueryProvider messageStoreQueryProvider() {
        return new MySqlChannelMessageStoreQueryProvider();
    }
}
