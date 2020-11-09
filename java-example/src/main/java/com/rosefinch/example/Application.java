package com.rosefinch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot启动
 */
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);



    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("\n\n*************************\n* Server start success! *\n*************************\n");
    }
}
