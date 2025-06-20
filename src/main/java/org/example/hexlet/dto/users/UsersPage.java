package org.example.hexlet.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.User;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UsersPage extends BasePage {
    private List<User> users;
    private String term;
}
