package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User extends Model {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<Post> posts;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        post.setAuthor(this);
        posts.add(post);
    }
}
