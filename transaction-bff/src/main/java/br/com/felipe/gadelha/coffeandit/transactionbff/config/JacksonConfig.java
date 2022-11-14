package br.com.felipe.gadelha.coffeandit.transactionbff.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class JacksonConfig {

//    @Autowired
//    private void registerSerializersDeserializers(List<ObjectMapper> objectMappers) {
//        SimpleModule simpleModule = new SimpleModule();
//        var dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
//        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
//        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
//        objectMappers.forEach(objectMapper -> objectMapper.registerModule(simpleModule));
//    }
//    @Bean
//    public ObjectMapper objectMapper() {
//        return JsonMapper.builder()
//            .findAndAddModules()
//            .build();
//    }
}
