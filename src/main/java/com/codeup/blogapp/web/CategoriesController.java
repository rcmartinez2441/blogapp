package com.codeup.blogapp.web;


import com.codeup.blogapp.data.Category;
import com.codeup.blogapp.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    List<Category> sampleCategories = new ArrayList<Category>();
    Collection<Post> samplePosts = new ArrayList<Post>();

    CategoriesController() {
        for (long i = 0; i < 5; i++) {
            samplePosts.add(new Post("Title" + i, "Content" + i, i));
            sampleCategories.add(new Category(i, "category" + i));
        }

        for (int i = 0; i < sampleCategories.size(); i++) {
            int numOfPostsToAdd = (int) Math.floor((Math.random() * 5) + 1);
            Collection<Post> randomPosts = new ArrayList<>();
            for (Post post : samplePosts) {
                if ( (post.getId() == numOfPostsToAdd) ) {
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
    private Category getPostsByCategory(@RequestParam String category) {
        for (Category thisCategory: sampleCategories){
            if (thisCategory.getName().equals(category)){
                return thisCategory;
            }
        }

        return null;
    }
}
