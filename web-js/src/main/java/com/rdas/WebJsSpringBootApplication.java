package com.rdas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by rdas on 23/07/2016.
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class WebJsSpringBootApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebJsSpringBootApplication.class, args);
    }
}
