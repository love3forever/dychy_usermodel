package com.dychy;

import com.dychy.model.User;
import com.dychy.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by eclipse on 2017/1/5.
 */
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private UserRepository repository;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String ...args) throws Exception {
        repository.deleteAll();
        repository.save(new User("wangmeng", 24));
        repository.save(new User("wangsan", 21));

        System.out.println("User found with findAll()");
        for (User u : repository.findAll()) {
            System.out.println(u);
        }



    }
}
