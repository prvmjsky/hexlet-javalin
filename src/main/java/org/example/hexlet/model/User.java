package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private long id;
    private String name;
    private List<Post> posts;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        posts = new ArrayList<>();
    }

    public void addPost(Post post) {
        post.setAuthor(this);
        posts.add(post);
    }
}
