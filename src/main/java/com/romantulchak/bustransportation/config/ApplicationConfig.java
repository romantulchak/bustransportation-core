package com.romantulchak.bustransportation.config;

import com.romantulchak.bustransportation.utility.EntityMapper;
import com.romantulchak.bustransportation.utility.EntityMapperInvoker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EntityMapper newEntityMapper(){
        return new EntityMapper();
    }

    @Bean
    public EntityMapperInvoker<Object, Object> newEntityMapperInvoker(){
        return new EntityMapperInvoker<>();
    }

}
