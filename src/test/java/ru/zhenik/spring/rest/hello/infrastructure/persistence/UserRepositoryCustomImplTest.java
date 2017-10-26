package ru.zhenik.spring.rest.hello.infrastructure.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zhenik.spring.rest.hello.model.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryCustomImplTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void createUser(){
        //Arrange
        User user = new User("userNamea", "passwordsad");
        //Act
        User u = userRepository.save(user);
        //Assert
        assertNotNull(u.getId());
    }
}