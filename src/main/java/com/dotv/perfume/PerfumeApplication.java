package com.dotv.perfume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class PerfumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfumeApplication.class, args);

    }

}
