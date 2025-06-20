package org.example.hexlet.dto.courses;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoursePage extends BasePage {
    private Course course;
}
