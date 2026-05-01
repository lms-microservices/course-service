package com.lms.course.service;

import com.lms.course.dto.CourseResponse;
import com.lms.course.dto.CreateCourseRequest;
import com.lms.course.dto.UpdateCourseRequest;
import com.lms.course.entity.Course;
import com.lms.course.enums.CourseStatus;
import com.lms.course.enums.DifficultyLevel;
import com.lms.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CreateCourseRequest request, Long instructorId) {
        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .thumbnailUrl(request.getThumbnailUrl())
                .difficultyLevel(request.getDifficultyLevel())
                .instructorId(instructorId)
                .status(CourseStatus.DRAFT)
                .featured(false)
                .createdAt(LocalDateTime.now())
                .build();

        return toResponse(courseRepository.save(course));
    }

    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return toResponse(course);
    }

    public List<CourseResponse> getAllPublishedCourses() {
        return courseRepository.findByStatus(CourseStatus.PUBLISHED)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<CourseResponse> searchCourses(String keyword, String category,
                                              DifficultyLevel difficulty,
                                              Double minPrice, Double maxPrice) {
        return courseRepository.searchCourses(category, difficulty, minPrice, maxPrice, keyword)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CourseResponse updateCourse(Long courseId, UpdateCourseRequest request, Long instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!course.getInstructorId().equals(instructorId)) {
            throw new RuntimeException("You are not the owner of this course");
        }

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setCategory(request.getCategory());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setDifficultyLevel(request.getDifficultyLevel());

        return toResponse(courseRepository.save(course));
    }

    public CourseResponse publishCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!course.getInstructorId().equals(instructorId)) {
            throw new RuntimeException("You are not the owner of this course");
        }

        course.setStatus(CourseStatus.PUBLISHED);
        return toResponse(courseRepository.save(course));
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<CourseResponse> getInstructorCourses(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setCourseId(course.getCourseId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setPrice(course.getPrice());
        response.setCategory(course.getCategory());
        response.setThumbnailUrl(course.getThumbnailUrl());
        response.setDifficultyLevel(course.getDifficultyLevel());
        response.setStatus(course.getStatus());
        response.setInstructorId(course.getInstructorId());
        response.setFeatured(course.getFeatured());
        return response;
    }
}