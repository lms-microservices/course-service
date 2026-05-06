package com.lms.course.dto;

import com.lms.course.enums.LessonType;

import lombok.Data;

@Data
public class LessonResponse {

    private Long lessonId;
    private String title;
    private LessonType type;
    private String contentUrl;
    private Integer duration;
    private Integer position;
    private Long courseId;
}