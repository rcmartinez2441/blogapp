package com.codeup.blogapp.data;

import java.util.Collection;

public class Post {

    private Long id;
    private String title;
    private String content;
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
