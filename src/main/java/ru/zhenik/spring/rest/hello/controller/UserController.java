package ru.zhenik.spring.rest.hello.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.zhenik.spring.rest.hello.infrastructure.persistence.UserRepository;
import ru.zhenik.spring.rest.hello.model.User;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * hello
 * NIK on 09/10/2017
 */
@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Long signup(@RequestBody Map<String, String> json) throws Exception {
        Long id = null;

        try{
            String name = json.get("username");
            String password = json.get("password");
            System.out.println(name+" : "+password);
            User user = new User(name,password);
            userRepository.save(user);
            id = user.getId();
        } catch (NumberFormatException e){
           throw new Exception("smth happened");
        }

        return id;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> json) throws Exception {
        String token = null;
        try{
            String name = json.get("username");
            String password = json.get("password");

            User user = userRepository.findUserByUsername(name);
            if (user!=null && password.equals(user.getPassword())){
                token = Jwts.builder()
                        .setSubject(String.valueOf(name))
                        .claim("roles", "user")
                        .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey")
                        .compact();
            } else {
               throw new Exception("Invalid input");
            }
        } catch (NumberFormatException e){
            throw new Exception("smth happened");
        }

        return token;
    }

    @RequestMapping(value = "/filter/me", method = RequestMethod.GET)
    public String me() throws Exception {
        return "Cors policy works";
    }
}
