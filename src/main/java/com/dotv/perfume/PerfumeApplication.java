package com.dotv.perfume;

import com.dotv.perfume.repository.TrademarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerfumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfumeApplication.class, args);
    }

}
