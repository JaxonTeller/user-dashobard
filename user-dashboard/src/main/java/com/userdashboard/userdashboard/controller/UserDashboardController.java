package com.userdashboard.userdashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.userdashboard.userdashboard.SerachKeyEventProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
@Slf4j
public class UserDashboardController {

    private final EurekaClient discoveryClient;
    private final RestTemplateBuilder restTemplateBuilder;
    private final RestTemplate restTemplate;
    private final SerachKeyEventProducer serachKeyEventProducer;

    /*A REST endpoint to get search history from USER-SEARCH-HISTORY service*/
    @HystrixCommand(fallbackMethod = "getUserSearchHistoryFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "20"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    @GetMapping("/serach/history")
    public ResponseEntity<List<String>> getUserSearchHistory()
    {
        InstanceInfo nextServerFromEureka = discoveryClient.getNextServerFromEureka("USER-SEARCH-HISTORY", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        log.info("Home page url for USER-SEARCH-HISTORY service "+homePageUrl);

        RestTemplate restTemplate = restTemplateBuilder.build();
        List<String> searchHistoryList = restTemplate.getForObject(homePageUrl + "/search/history", List.class);
        return ResponseEntity.status(HttpStatus.OK).body(searchHistoryList);
    }

    public ResponseEntity<List<String>> getUserSearchHistoryFallback()
    {
        return ResponseEntity.status(HttpStatus.OK).body(List.of("More","Generalized","Search","History"));
    }

    /*A REST endpoint to get dashboard content for User tailored according to User search history,
    Using USER-DASHBOARD-CONTENT service*/
    @GetMapping("/dashboard/{searchKey}")
    public ResponseEntity<List<String>> getUserDashboardContent(@PathVariable String searchKey)
    {
        List<String> dashboardContentAccToSearchKey =
                restTemplate.getForObject("http://user-dashboard-content/dashboard/content/Shoes", List.class);
        return ResponseEntity.status(HttpStatus.OK).body(dashboardContentAccToSearchKey);
    }

    /*A REST endpoint which puts an search event into Kafka, For USER-SEARCH-HISTORY service to Consume*/
    @GetMapping("/user/search/{searchKey}")
    public void generateAnSearchEventForUser(@PathVariable String searchKey) throws JsonProcessingException {

        //invoke kafka producer
        serachKeyEventProducer.sendSearhKeyMessageToKafka(searchKey);
    }

}
