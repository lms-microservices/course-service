package com.lms.course.controller;

import com.lms.course.dto.CourseResponse;
import com.lms.course.dto.CreateCourseRequest;
import com.lms.course.dto.UpdateCourseRequest;
import com.lms.course.enums.DifficultyLevel;
import com.lms.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CreateCourseRequest request,
            @RequestHeader("X-User-Id") Long instructorId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourse(request, instructorId));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllPublishedCourses() {
        return ResponseEntity.ok(courseService.getAllPublishedCourses());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseResponse>> searchCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) DifficultyLevel difficulty,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return ResponseEntity.ok(courseService.searchCourses(
                keyword, category, difficulty, minPrice, maxPrice));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody UpdateCourseRequest request,
            @RequestHeader("X-User-Id") Long instructorId) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, request, instructorId));
    }

    @PutMapping("/{courseId}/publish")
    public ResponseEntity<CourseResponse> publishCourse(
            @PathVariable Long courseId,
            @RequestHeader("X-User-Id") Long instructorId) {
        return ResponseEntity.ok(courseService.publishCourse(courseId, instructorId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/instructor")
    public ResponseEntity<List<CourseResponse>> getInstructorCourses(
            @RequestHeader("X-User-Id") Long instructorId) {
        return ResponseEntity.ok(courseService.getInstructorCourses(instructorId));
    }
}