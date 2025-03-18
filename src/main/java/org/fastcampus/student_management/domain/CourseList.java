package org.fastcampus.student_management.domain;

import java.util.List;

//1급 컬렉션
public class CourseList {

    private final List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    //서비스레이어 변경 없음
    //테스트가 쉬워짐 (Repository 주입 없이 테스트 가능)
    //한눈에 기능 확인이 쉬워짐
    public void changeAllCoursesFee(int fee){
        courses.forEach(course -> {
            if(course.isSameDay(DayOfWeek.SATURDAY) || course.isSameDay(DayOfWeek.SUNDAY)){
                course.changeFee((int)(fee * 1.5));
            }
            course.changeFee(fee);
        });
    }
}
