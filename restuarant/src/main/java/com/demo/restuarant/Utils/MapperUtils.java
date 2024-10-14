package com.demo.restuarant.Utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
