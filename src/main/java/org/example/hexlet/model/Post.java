package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post extends Model {
    private long id;
    private String title;
    private String content;
    private User author;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
