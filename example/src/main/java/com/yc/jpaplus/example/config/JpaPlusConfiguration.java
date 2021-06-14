package com.yc.jpaplus.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaPlusConfiguration {
    @Bean
    @ConditionalOnMissingBean(name="jpaPlusAuditorAware")
    JpaPlusAuditorAware jpaPlusAuditorAware(){
        return new JpaPlusAuditorAware();
    }
}
