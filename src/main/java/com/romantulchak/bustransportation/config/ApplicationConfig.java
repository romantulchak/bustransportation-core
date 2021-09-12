package com.romantulchak.bustransportation.config;

import com.mapperDTO.mapper.EntityMapper;
import com.mapperDTO.mapper.EntityMapperInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.mapperDTO", "com.ecfinder"})
public class ApplicationConfig {

    private ApplicationContext applicationContext;

    @Bean
    public EntityMapper newEntityMapper(){
        return new EntityMapper();
    }

    @Bean
    public EntityMapperInvoker<Object, Object> newEntityMapperInvoker(){
        return new EntityMapperInvoker<>();
    }


    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
