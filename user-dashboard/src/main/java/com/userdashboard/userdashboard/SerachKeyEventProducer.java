package com.userdashboard.userdashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
@AllArgsConstructor
@Slf4j
public class SerachKeyEventProducer {

    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public void sendSearhKeyMessageToKafka(String searchKey) throws JsonProcessingException {
        String key="DEFAULT-USER-NAME";
        String data=objectMapper.writeValueAsString(searchKey);

        ProducerRecord<String,String> producerRecord=new ProducerRecord<>("user-search",key,data);

        ListenableFuture<SendResult<String, String>> futureForRecordBatch
                = kafkaTemplate.send(producerRecord);

        SuccessCallback<SendResult<String, String>> successCallback=(SendResult<String, String> sendResultOnSucess)->{
            log.info("Successfully published to topic "+sendResultOnSucess.getProducerRecord());
        };
        FailureCallback failureCallback=(Throwable throwable)->{
          log.info("Failed to publish message on kafka"+throwable.getStackTrace());
        };
        futureForRecordBatch.addCallback(successCallback,failureCallback);
    }
}
