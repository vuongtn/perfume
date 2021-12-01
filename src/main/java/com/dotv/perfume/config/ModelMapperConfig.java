package com.dotv.perfume.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /*
    *   Standard: không quan tâm thứ tự
        Loose: không quan tâm thứ tự
        Strict: Thứ tự từ phải đúng
    * */
    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
