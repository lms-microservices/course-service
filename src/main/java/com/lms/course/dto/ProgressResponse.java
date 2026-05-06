package com.lms.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressResponse {

    private Long courseId;
    private Long studentId;
    private int totalLessons;
    private int completedLessons;
    private double progressPercentage;
}