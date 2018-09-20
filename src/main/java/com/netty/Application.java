package com.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
//@ComponentScan({"com.netty.cache"})
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
    		logger.info("start spring boot .....");
		SpringApplication.run(Application.class, args);
		
    }
}   