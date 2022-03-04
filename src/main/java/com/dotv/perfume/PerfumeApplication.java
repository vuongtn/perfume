package com.dotv.perfume;

import com.dotv.perfume.repository.TrademarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PerfumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfumeApplication.class, args);
    }

}
