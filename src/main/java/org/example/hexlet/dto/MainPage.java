package org.example.hexlet.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainPage {
    private Boolean visited;

    public boolean isVisited() {
        return visited;
    }
}
