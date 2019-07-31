package com.kq.activemq.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
public class Consumer {

	@JmsListener(destination = "mailbox")
	public void receive(Email email) {
		System.out.println("Received <" + email + ">");
	}

	public static void main(String[] args) {
		SpringApplication.run(Consumer.class, args);
	}
}
