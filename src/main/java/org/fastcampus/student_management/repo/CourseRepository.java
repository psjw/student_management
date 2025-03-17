package org.fastcampus.student_management.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;

public class CourseRepository {

    private final Map<String, Course> courseMap = new HashMap<>();

    public void save(Course course) {
        courseMap.put(course.getCourseName(), course);
    }
//
//  public List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
//    List<Course> courses = new ArrayList<>();
//    for (Course course : courseMap.values()) {
//      if (course.isSameDay(dayOfWeek) && course.isActivateUser()) {
//        courses.add(course);
//      }
//    }
//    return courses;
//  }

//    public List<Course> getCourseListByStudent(String studentName) {
//        List<Course> courses = new ArrayList<>();
//        for (Course course : courseMap.values()) {
//            if (course.getStudentName().equals(studentName)) {
//                courses.add(course);
//            }
//        }
//        return courses;
//    }
//
//    public void saveCourses(List<Course> courses) {
//        for (Course course : courses) {
//            courseMap.put(course.getCourseName(), course);
//        }
//    }

    public List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
        return courseMap.entrySet().stream()
                .filter(entry -> entry.getValue().isSameDay(dayOfWeek))
                .filter(entry -> entry.getValue().isActivateUser())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Course>  getCourseListByStudent(String studentName) {
        return courseMap.entrySet().stream()
                .filter(entry -> entry.getValue().isSameStudentName(studentName))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void saveCourses(List<Course> courses) {
        courses.forEach(this::save);
    }
}
