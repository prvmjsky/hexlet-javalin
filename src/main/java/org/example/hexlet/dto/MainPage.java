package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MainPage extends BasePage {
    private Boolean visited;
    private String currentUser;

    public MainPage(Boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }
}
