package com.codeup.blogapp.data.post;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name  = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column (nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name= "user_id") //Makes foreign key for user_id in post table
    private User user;


    private Collection<Category> categories;

    public Post() {
    }

    public Post(String title, String content, Long id, Collection<Category> categories){
        this.title = title;
        this.content = content;
        this.id = id;
        this.categories = categories;
    }

    public Post(String title, String content, Long id, Collection<Category> categories, User user){
        this.title = title;
        this.content = content;
        this.id = id;
        this.categories = categories;
        this.user = user;
    }

    public Post(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}
