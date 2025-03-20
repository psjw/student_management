package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.domain.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

public class CourseService {

    private final CourseCommandRepository courseCommandRepository;
    private final CourseQueryRepository courseQueryRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseCommandRepository courseCommandRepository, CourseQueryRepository courseQueryRepository, StudentRepository studentRepository) {
        this.courseCommandRepository = courseCommandRepository;
        this.courseQueryRepository = courseQueryRepository;
        this.studentRepository = studentRepository;
    }

    public void registerCourse(CourseInfoDto courseInfoDto) {
        Student student = studentRepository.getStudent(courseInfoDto.getStudentName());
        Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(),
                courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
        courseCommandRepository.save(course);
    }

    public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
        // TODO: 과제 구현 부분
        List<Course> courseDayOfWeeks = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);
        if (courseDayOfWeeks.isEmpty()) {
            throw new RuntimeException("요텅하신 날짜에 해당하는 수업이 없습니다.");
        }
        return courseDayOfWeeks.stream().map(CourseInfoDto::new)
                .collect(Collectors.toList());
    }

    public void changeFee(String studentName, int fee) {
        // TODO: 과제 구현 부분
        List<Course> cources = courseQueryRepository.getCourseListByStudent(studentName);
        CourseList courseList = new CourseList(cources);
        courseList.changeAllCoursesFee(fee);
    }
}
