package com.codeup.blogapp.web;

import com.codeup.blogapp.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json", produces="application/json")
public class UsersController {
    private List<User> userList = new ArrayList<>();
    UsersController(){
        userList.add(new User(1, "poop", "poop@gmail", "notpoop"));
        userList.add(new User(2, "woof", "woof@gmail", "notwoof"));
        userList.add(new User(3, "gabagoole", "gabagoole@gmail", "notgabagoole"));
    }

    //***** CREATE *****
    @PostMapping
    private void createUser(@RequestBody User newPost){
    }

    //*****READ*****
    @GetMapping
    private List<User> getUsers(){
        return userList;
    }

    @GetMapping("/{id}")
    private User getUserByID(@PathVariable Long id){
        return null;
    }

    //******UPDATE*****
    @PutMapping("/{id}")
    private void updateUser(@RequestBody String updatedUser){
        System.out.println("Put Works");
    }

    //***** DELETE *****
    @DeleteMapping("/{id}")
    private void deleteUser(){
        System.out.println("Delete Works");
    }
}
