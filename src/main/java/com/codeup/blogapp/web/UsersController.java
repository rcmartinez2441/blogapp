package com.codeup.blogapp.web;

import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json", produces="application/json")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //***** CREATE *****
    @PostMapping
    private void createUser(@RequestBody User newUser){
        userRepository.save(newUser);

        System.out.println(newUser.getId());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPassword());
    }


    //========================================================================
    //*****READ*****
    @GetMapping
    private List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable Long id){
        return userRepository.getById(id);
    }

    @GetMapping("/findByUsername")
    private User findByUsername(@RequestParam String userName){
       return userRepository.findFirstByUsername(userName);
    }

    @GetMapping("/findByEmail")
    private User findByEmail(@RequestParam String email){
        return userRepository.findFirstByEmail(email);
    }

    //========================================================================
    //******UPDATE*****
    @PutMapping("/{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userRepository.save(updatedUser);
        System.out.println("Put Works");
    }

    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
             userRepository.getById(id);
    }

    //========================================================================
    //***** DELETE *****
    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        System.out.println("Delete Works");
    }
}
