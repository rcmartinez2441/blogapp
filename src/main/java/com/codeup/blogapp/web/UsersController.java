package com.codeup.blogapp.web;

import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json", produces="application/json")
public class UsersController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //***** CREATE *****
    @PostMapping("/create")
    @PreAuthorize("!hasAuthority('USER')") //This is coming from the token in header of request, if there's no token, then no authorities and then return true. One way to apply specific authorization to a specific method
    private void createUser(@RequestBody User newUser){
        System.out.println(newUser.getId());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPassword());

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

    }


    //========================================================================
    //*****READ*****
    @GetMapping
    private List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/token") //now your user view has all the user data
    private User getCurrentUserFromToken(OAuth2Authentication auth){ //tyring to get info about the person logged in
        String email = auth.getName();
        return userRepository.findByEmail(email).get();
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
        return userRepository.findByEmail(email).get();
    }

    //========================================================================
    //******UPDATE*****
    @PutMapping("/{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userRepository.save(updatedUser);
        System.out.println("Put Works");
    }

    @PutMapping("/{id}/updatePassword")
    @PreAuthorize("authentication.credentials.equals(#oldPassword)")
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
