package com.codeup.blogapp.data.user;


import com.codeup.blogapp.data.post.Post;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 100)
    private String username;

    @Email
    @Column(nullable = false)
    private String email;

    @Column (nullable = false, length = 150)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)//We will see either USER or ADMIN in db table on column
    private Role role = Role.USER;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post") //Cascade means If I were to delete a user, it would delete any posts that has that user ID
    //What are these posts mapped by?
    private Collection<Post> posts; //Collection is for when we hook up our database, it wants a very generic. it can turn it into an arraylist or something else

    public enum Role {USER, ADMIN};

    public User(){
    }

    public User(long id, String username, String email, String password, Collection<Post> posts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public User (String userName){
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
