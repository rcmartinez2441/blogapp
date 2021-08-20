package com.codeup.blogapp.data.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository  extends JpaRepository<Post, Long> {

    //Spring will take care of the query selector based on just this syntax for this method. 'findBy' = 'SELECT' , 'byTitle' = colunm name in table
    Post findByTitle(String title);
}
