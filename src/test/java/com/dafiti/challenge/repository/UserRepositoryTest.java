package com.dafiti.challenge.repository;

import java.util.Optional;

import com.dafiti.challenge.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void usuarioPorClient(){
        String client = "dafiti";
        Optional<User> user = userRepository.findByClient(client);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void errorUsuarioPorClient(){
        String client = "----lll";
        Optional<User> user = userRepository.findByClient(client);
        Assert.assertEquals(user.isPresent(),false);
    }
    
}
