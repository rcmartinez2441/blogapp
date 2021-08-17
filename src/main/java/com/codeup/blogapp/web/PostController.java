package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Post;
import com.codeup.blogapp.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//Your user shouldnt see '/api/posts' its more for backend
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {


    @GetMapping
    private List<Post> getPost() { // get BLOG Posts not Post request
        User user = new User(1, "testUser", "test@gmail.com", "test123", null);

        List<Post> posts = new ArrayList<>();
        posts.add(new Post(69L, "Clifford Joins the Army", "War, war never changes", user));
        posts.add(new Post(420L, "Clifford Invests In Cryptocurrency", "He lost alot of money on DogeCoin", user));
        posts.add(new Post(9L, "Clifford Finds Infinity Gauntlet", "Reality can be anything I want *SNAPS* infinite dog treats", user));

        return posts;
    }

    //Ex. someone wants to make a get request with specific constraints (user only wants one post back based on ID
    //Ex. someone is making a request to like "/api/posts/1 - with 1 being ID
    @GetMapping("/{id}")
    private Post getPostByID(@PathVariable Long id) {
        User user = new User(1, "testUser", "test@gmail.com", "test123", null);

        if (id == 1) {
            return new Post(69L, "test1", "fdsfdsfafds",user);
        } else {
            return null;
        }
    }

    @PostMapping
    //It will looks at the fetch request and find the body property
    //"I should be able to transform it to any kind of object, in this case a Post Object"
    private void createPost(@RequestBody Post newPost) {
        System.out.println(newPost.getId());
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());
    }

    @PutMapping("/{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        System.out.println(updatedPost.getId());
        System.out.println(updatedPost.getTitle());
        System.out.println(updatedPost.getContent());
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable Long id) {
        System.out.println("You successfully deleted post with ID: " + id);
    }

}
