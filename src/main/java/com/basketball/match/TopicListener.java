package com.basketball.match;

import com.basketball.match.entity.User;
import com.basketball.match.service.UserService;
import com.basketball.match.wrapper.UserWrapper;
import com.basketball.match.model.Example;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

    @Value("${topic.name.consumer")
    private String topicName;

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> payload){log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        // Now do the magic.
        Example data = new Gson().fromJson(payload.value(), Example.class);

        log.info("Data" , data);

        UserWrapper userWrapper = new UserWrapper();

        if(data.getPayload().getOp().equals("c")) {
            log.info("Se tiene que crear");
            userWrapper.setEmail(data.getPayload().getAfter().getEmail());
            userWrapper.setId(data.getPayload().getAfter().getId().longValue());
            userWrapper.setFirstName(data.getPayload().getAfter().getFirstName());
            userWrapper.setLastName(data.getPayload().getAfter().getLastName());
            User user = convertUserWrapperToEntity(userWrapper);
            log.info("User {}" , user);
            userService.createUser(user);
        }

        if(data.getPayload().getOp().equals("U")){
            log.info("Se tiene que actualizar");
        }
        if(data.getPayload().getOp().equals("D")){
            log.info("Se tiene que borrar");
        }
        if(data.getPayload().getOp().equals("r")){
            log.info("Se tiene que leer");
        }
    }

    private User convertUserWrapperToEntity(UserWrapper userWrapper) {
        User user = new User ();
        user.setId(userWrapper.getId());
        user.setFirstName(userWrapper.getFirstName());
        user.setLastName(userWrapper.getLastName());
        return user;
    }

}
