package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostController {

    @GetMapping("{id}")
    public List<Post> getPost(@PathVariable Long id){
        List<Post> listOfPosts = new ArrayList<>();

        listOfPosts.add( new Post(69L,"test1", "fdsfdsfafds"));
        listOfPosts.add( new Post(420L,"test2", "fdsfdsfafdsfdsfdsfafdsfdsfdsfafdsfdsfdsfafds"));
        listOfPosts.add( new Post(9L,"test2", "fdsfdsfafds fdsfdsfafds fdsfdsfafds"));

        return listOfPosts;

    };

}
