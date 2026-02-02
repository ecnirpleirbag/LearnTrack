package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String description, int duration) {
        Course course = new Course(IdGenerator.getNextCourseId(), name, description, duration, true);
        courseRepository.save(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) throws EntityNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " not found."));
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(false);
    }
}
