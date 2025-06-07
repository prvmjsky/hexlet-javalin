package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private long id;
    private String title;
    private String content;
    private User author;

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
