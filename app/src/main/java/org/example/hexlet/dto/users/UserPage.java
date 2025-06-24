package org.example.hexlet.dto.users;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPage extends BasePage {
    private User user;
}
