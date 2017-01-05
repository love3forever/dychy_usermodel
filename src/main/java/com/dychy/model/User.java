package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/5.
 */
public class User {
    @Id
    public String id;

    public String username;
    public int age;

    public User(){

    }

    public User(String username,int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString(){
        return String.format("User[id=%s ,username=%s, age=%d]", id, username, age);
    }

}

