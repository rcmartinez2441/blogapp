package com.codeup.blogapp.web;


import com.codeup.blogapp.data.category.CategoriesRepository;
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

    private CategoriesRepository categoriesRepository;

    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping
    private List<Category> getCategories (){
        return categoriesRepository.findAll();
    }

    @GetMapping("/{name}")
    private Category getPostsByCategory(@PathVariable String name) {
        return categoriesRepository.findFirstByName(name);
    }
}
