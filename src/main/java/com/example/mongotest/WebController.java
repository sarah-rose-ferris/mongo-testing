package com.example.mongotest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
public class WebController {

    @GetMapping("/hello")
    public String greeting(){
        return "Hello World";
    }

    @GetMapping("/fetcher")
    public ResponseEntity<String> getTest(){
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.getForEntity("https://reqres.in/api/users?page=2",String.class);
        System.out.println(response.toString());
        return  response;
    }

    @GetMapping("/fetchercustom")
    public ResponseEntity<String> getTestCustom(@RequestParam("id") int id){
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.getForEntity("https://reqres.in/api/users/" + id,String.class);
        System.out.println(response.toString());
        return  response;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        DataGenerator dataGenerator = new DataGenerator();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(dataGenerator.dataForUser(),headers);
        ResponseEntity<String> response = template.postForEntity("https://reqres.in/api/users",entity, String.class);
        System.out.println(response.toString());
        return  response;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        DataGenerator dataGenerator = new DataGenerator();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(dataGenerator.dataForUser(),headers);
        ResponseEntity<String> response = template.exchange("https://reqres.in/api/users/2", HttpMethod.PUT,entity, String.class);
        System.out.println(response.toString());
        return  response;
    }

    @PutMapping("/updateUserCustom")
    public ResponseEntity<String> updateUserCustom(String name, String job){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        DataGenerator dataGenerator = new DataGenerator();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(dataGenerator.dataForUserCustom(name,job),headers);
        ResponseEntity<String> response = template.exchange("https://reqres.in/api/users/2", HttpMethod.PUT,entity, String.class);
        System.out.println(response.toString());
        return  response;
    }
}
