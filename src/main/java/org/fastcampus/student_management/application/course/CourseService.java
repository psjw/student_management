package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courseDayOfWeeks = courseRepository.getCourseDayOfWeek(dayOfWeek);
    if(courseDayOfWeeks.isEmpty()){
      throw new RuntimeException("요텅하신 날짜에 해당하는 수업이 없습니다.");
    }
    return courseDayOfWeeks.stream().map(CourseInfoDto::new)
            .collect(Collectors.toList());
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    Student student = studentService.getStudent(studentName);
    List<Course> coursesByStudent = courseRepository.getCourseListByStudent(studentName);
    List<Course> changeCoursesByStudent = coursesByStudent.stream()
            .map(course -> new Course(student, course.getCourseName(), fee, course.getDayOfWeek(),
                    course.getCourseTime()))
            .toList();

    courseRepository.saveCourses(changeCoursesByStudent);

  }
}
