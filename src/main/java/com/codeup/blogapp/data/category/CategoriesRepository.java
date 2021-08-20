package com.codeup.blogapp.data.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository  extends JpaRepository<Category, Long> {
    Category findFirstByName(String name);
}
