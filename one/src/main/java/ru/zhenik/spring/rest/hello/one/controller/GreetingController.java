package ru.zhenik.spring.rest.hello.one.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/inside")
    public String chaining() {
        return "inside module 1";
    }

    @RequestMapping("/outside")
    public String outside() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082/me", String.class);
        return "Chaining + " + response.getBody();
    }
}
