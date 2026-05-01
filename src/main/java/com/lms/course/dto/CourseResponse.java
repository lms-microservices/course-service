package com.lms.course.dto;

import com.lms.course.enums.CourseStatus;
import com.lms.course.enums.DifficultyLevel;
import lombok.Data;

@Data
public class CourseResponse {
    private Long courseId;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String thumbnailUrl;
    private DifficultyLevel difficultyLevel;
    private CourseStatus status;
    private Long instructorId;
    private Boolean featured;
}