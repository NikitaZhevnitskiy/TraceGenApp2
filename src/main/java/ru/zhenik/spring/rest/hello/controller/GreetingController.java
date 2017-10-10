package ru.zhenik.spring.rest.hello.controller;

import io.opentracing.ActiveSpan;
import io.opentracing.Span;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.zhenik.spring.rest.hello.entity.Greeting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * hello
 * NIK on 09/10/2017
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private io.opentracing.Tracer tracer;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        try (ActiveSpan activeSpan = tracer.buildSpan("someWork").withTag("greetingCall",counter.incrementAndGet()).startActive()) {
            return new Greeting(counter.get(),
                    String.format(template, name));
        }
    }

    @RequestMapping("/chaining")
    public String chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/greeting", String.class);
        return "Chaining + " + response.getBody();
    }
}
