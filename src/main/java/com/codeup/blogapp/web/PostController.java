package com.codeup.blogapp.web;

import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.post.PostsRepository;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import com.codeup.blogapp.services.EmailService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
//Your user shouldnt see '/api/posts' its more for backend
@RequestMapping(value = "/api/posts", headers = "Accept=application/json", produces = "application/json")
public class PostController {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, EmailService emailService, UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.emailService = emailService;
        this.userRepository  = userRepository;
    }

    @GetMapping
    private List<Post> getPost() {
        return postsRepository.findAll();
    }

    //Ex. someone wants to make a get request with specific constraints (user only wants one post back based on ID
    //Ex. someone is making a request to like "/api/posts/1 - with 1 being ID
    @GetMapping("/{id}")
    private Optional<Post> getPostByID(@PathVariable Long id) {
        return postsRepository.findById(id);
    }

    @PostMapping
    //It will looks at the fetch request and find the body property
    //"I should be able to transform it to any kind of object, in this case a Post Object"
    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        System.out.println(newPost.getId());
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());
        String email = auth.getName();
        User user = userRepository.findByEmail(email).get();
        newPost.setUser(user);
        postsRepository.save(newPost);

        emailService.prepareAndSend(newPost, "Testing", "Testing to see if this works");
    }

    @PutMapping("/update")
    private void updatePost(@RequestBody Post updatedPost) {
        System.out.println(updatedPost.getTitle());
        System.out.println(updatedPost.getContent());
        //We need to take this id and need to to a get by id and get a post back that matches the id and make sure that it exists in order to update instead of creating a new post
        postsRepository.save(updatedPost);
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable Long id) {
        System.out.println("You successfully deleted post with ID: " + id);
        postsRepository.deleteById(id);
    }

}
