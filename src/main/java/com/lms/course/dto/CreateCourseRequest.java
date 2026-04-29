package com.lms.course.dto;

import com.lms.course.enums.DifficultyLevel;
import lombok.Data;

@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String thumbnailUrl;
    private DifficultyLevel difficultyLevel;
}