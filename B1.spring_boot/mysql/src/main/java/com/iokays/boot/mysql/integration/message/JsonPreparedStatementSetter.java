package com.iokays.boot.mysql.integration.message;

import org.springframework.integration.jdbc.store.channel.ChannelMessageStorePreparedStatementSetter;
import org.springframework.messaging.Message;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JsonPreparedStatementSetter extends ChannelMessageStorePreparedStatementSetter {

    @Override
    public void setValues(PreparedStatement preparedStatement, Message<?> requestMessage,
                          Object groupId, String region, boolean priorityEnabled) throws SQLException {
        // Populate common columns
        super.setValues(preparedStatement, requestMessage, groupId, region, priorityEnabled);
        // Store message payload as varchar
        preparedStatement.setString(6, requestMessage.getPayload().toString());
    }
}