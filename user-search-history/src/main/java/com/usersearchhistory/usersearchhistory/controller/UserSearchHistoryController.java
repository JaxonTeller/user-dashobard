package com.usersearchhistory.usersearchhistory.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
public class UserSearchHistoryController {

    /*An REST endpoint which will provide the search history to consumer services*/
    @GetMapping("/history")
    public List<String> getUserSearchHistory()
    {
        return List.of("This","Is","Hardcoded","User","History");
    }

    /*An endpoint which will consume the search event from kafka
    and update the search history for the User*/
    @KafkaListener(topics = {"user-search"})
    public void consumeSearchEventAndUpdateUserSearchHistory(ConsumerRecord<String,String> userSearchHistoryMessages)
    {
            log.info("Consumer records "+userSearchHistoryMessages);
    }
}
