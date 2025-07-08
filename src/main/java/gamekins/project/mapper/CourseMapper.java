package gamekins.project.mapper;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;

public final class CourseMapper {

    private CourseMapper() {}

    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setCode(course.getCode());
        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        return course;
    }

    public static void updateEntityFromDto(Course course, CourseDTO dto) {
        course.setName(dto.getName());
        course.setCode(dto.getCode());
    }
}