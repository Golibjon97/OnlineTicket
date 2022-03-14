package com.ticket.onlineticket.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class Util {

    @Bean
    public DecimalFormat decimalFormat(){
        return new DecimalFormat();
    }
}
