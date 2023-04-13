package com.basketball.match.consumer;

import com.basketball.match.events.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Slf4j
@Component
public class Consumer {
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public Consumer(ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(
            topics = {"${topic.name}"},
            groupId = "${kafka.group}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenEvents(Event event){
        if (Objects.nonNull(event.getPayload().getSource())){
            log.info("New event consumed successfully");
        }else{
            log.error("The event has been discarded for not having the expected information");
        }
    }

}
