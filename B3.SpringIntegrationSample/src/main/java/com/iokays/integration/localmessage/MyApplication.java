package com.iokays.integration.localmessage;

import com.iokays.integration.localmessage.event.CreatedOrderEvent;
import com.iokays.integration.localmessage.publisher.DomainPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableTransactionManagement
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);

	}

}
