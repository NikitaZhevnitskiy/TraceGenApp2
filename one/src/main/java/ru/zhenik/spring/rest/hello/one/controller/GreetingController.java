package ru.zhenik.spring.rest.hello.one.controller;


import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${module-two.host}")
    private String hostTwo;

    @Value("${module-two.port}")
    private Integer port;

    @RequestMapping("/inside")
    public String chaining() {
        return "inside module 1";
    }

    @RequestMapping("/buybook")
    public ResponseEntity buyBookMethod() {
        String url = "http://"+hostTwo+":"+port+"/getbook";
        Random r = new Random();
        int chance = r.nextInt(6);
        if (chance>2) return ResponseEntity.ok(restTemplate.getForEntity(url, String.class).getBody());
        return ResponseEntity.badRequest().body("Not enough money you have: "+chance);
    }
}
