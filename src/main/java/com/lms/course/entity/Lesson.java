package com.lms.course.entity;

import com.lms.course.enums.LessonType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    private String title;

    @Enumerated(EnumType.STRING)
    private LessonType type;

    private String contentUrl;
    private Integer duration;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}