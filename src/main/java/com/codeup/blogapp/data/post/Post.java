package com.codeup.blogapp.data.post;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
//    @JsonBackReference - this will excluded everything but was replaced by @JsonIgnoreProperties to allow a bit of limited recurssion to access some user stuff
    //Makes foreign key for user_id in post table
    @JsonIgnoreProperties({"posts", "password"}) // WIll ignore post and password properties from user to avoid recurssion
    private User user;

    @ManyToMany (
            fetch = FetchType.LAZY,
            cascade = {/*CascadeType.MERGE, */CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
            targetEntity = Category.class
    )
//    @JsonIgnore // When you get a Post object, you will see the user but no posts
    @JoinTable(
            name="post_category",
            //What are your two foreign keys for your two tables
            joinColumns = {@JoinColumn(name = "post_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    @JsonIgnoreProperties("posts")
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
