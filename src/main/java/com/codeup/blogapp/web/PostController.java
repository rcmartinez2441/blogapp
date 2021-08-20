package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.post.PostsRepository;
import com.codeup.blogapp.data.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
//Your user shouldnt see '/api/posts' its more for backend
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    private final PostsRepository postsRepository;

    public PostController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    private List<Post> getPost() {
        return postsRepository.findAll();
    }

    //Ex. someone wants to make a get request with specific constraints (user only wants one post back based on ID
    //Ex. someone is making a request to like "/api/posts/1 - with 1 being ID
    @GetMapping("/{id}")
    private Post getPostByID(@PathVariable Long id) {
        return postsRepository.getById(id);
    }

    @PostMapping
    //It will looks at the fetch request and find the body property
    //"I should be able to transform it to any kind of object, in this case a Post Object"
    private void createPost(@RequestBody Post newPost) {
        System.out.println(newPost.getId());
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());

        postsRepository.save(newPost);
    }

    @PutMapping("/{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        System.out.println(updatedPost.getId());
        System.out.println(updatedPost.getTitle());
        System.out.println(updatedPost.getContent());
        //We need to take this id and need to to a get by id and get a post back that matches the id and make sure that it exists in order to update instead of creating a new post
        Post existingPost = postsRepository.getById(id);
        if(existingPost == null)
        postsRepository.save(updatedPost);
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable Long id) {
        System.out.println("You successfully deleted post with ID: " + id);
        postsRepository.deleteById(id);
    }

}
