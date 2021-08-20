package com.codeup.blogapp.web;


import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    List<Category> sampleCategories = new ArrayList<Category>();
    Collection<Post> samplePosts = new ArrayList<Post>();

    CategoriesController() {
        //Loops to add 5 categories and posts to each respective variable
        for (long i = 1; i < 6; i++) {
            samplePosts.add(new Post(i,"Title" + i, "Content" + i, new User("user1")));
            sampleCategories.add(new Category(i, "category" + i));
        }

        //Loops up until the total amount of Categories then inserts a random number of posts in samplePosts per category
        for (int i = 0; i < sampleCategories.size(); i++) {
            int numOfPostsToAdd = (int) Math.floor((Math.random() * 5) + 1);
            Collection<Post> randomPosts = new ArrayList<>();
            for (Post post : samplePosts) {
                if ( (post.getId() == numOfPostsToAdd) && (post.getId() > 1) ) {
                    break;
                } else{
                    randomPosts.add(post);
                }
            }
            sampleCategories.get(i).setPosts(randomPosts);
        }
        System.out.println(samplePosts);
    }

    @GetMapping
    private List<Category> getCategories (){
        return sampleCategories;
    }

    @GetMapping("/{name}")
    private Category getPostsByCategory(@PathVariable String name) {
        for (Category thisCategory: sampleCategories){
            if (thisCategory.getName().equals(name)){
                return thisCategory;
            }
        }

        return null;
    }
}
