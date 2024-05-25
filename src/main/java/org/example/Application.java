package org.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@Slf4j
@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = {
        "org.example.kafka",
        "org.example.service",
        "org.example.model"
})
@PropertySource({
        "classpath:kafka.properties"
})
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        LOGGER.info("Старт приложения");
        log.info("Старт приложения");
        LOGGER.warn("Старт приложения");
    }
}
