package com.codeup.blogapp.web;

import com.codeup.blogapp.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json", produces="application/json")
public class UsersController {
    private List<User> userList = new ArrayList<>();
    UsersController(){
        userList.add(new User(1, "poop", "poop@gmail", "notpoop"));
        userList.add(new User(2, "woof", "woof@gmail", "notwoof"));
        userList.add(new User(3, "gabagoole4", "gabagoole4@gmail", "notgabagoole"));
        userList.add(new User(4, "gabagoole5", "gabagoole5@gmail", "notgabagoole"));
        userList.add(new User(5, "gabagoole5", "gabagoole6@gmail", "notgabagoole"));
    }

    //***** CREATE *****
    @PostMapping
    private void createUser(@RequestBody User newUser){
        long id = userList.size() + 1;
        newUser.setId(id);
        System.out.println(newUser.getId());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPassword());
        userList.add(new User(id, newUser.getUsername(), newUser.getEmail(), newUser.getPassword() ) );
    }

    //========================================================================
    //*****READ*****
    @GetMapping
    private List<User> getUsers(){
        return userList;
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable Long id){
        System.out.println(id);
        for (User user: userList){
            if(user.getId() == id){
                System.out.println(user.getId());
                System.out.println(user.getEmail());
                return user;
            }
        }
        return null;
    }

    @GetMapping("/findByUsername")
    private User findByUsername(@RequestParam String userName){
        for(User user: userList){
            if(user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @GetMapping("/findByEmail")
    private User findByEmail(@RequestParam String email){
        for(User user: userList){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    //========================================================================
    //******UPDATE*****
    @PutMapping("/{id}")
    private void updateUser(@RequestBody String updatedUser){
        System.out.println("Put Works");
    }

    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        for(User user : userList){
            if (user.getId() == id){
                System.out.println(user.getPassword());
                user.setPassword(newPassword);
                System.out.println(user.getPassword());
            }
        }
    }

    //========================================================================
    //***** DELETE *****
    @DeleteMapping("/{id}")
    private void deleteUser(){
        System.out.println("Delete Works");
    }
}
