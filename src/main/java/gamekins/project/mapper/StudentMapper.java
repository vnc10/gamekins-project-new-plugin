package gamekins.project.mapper;

import gamekins.project.domain.Course;
import gamekins.project.domain.Student;
import gamekins.project.domain.dto.StudentDTO;

public final class StudentMapper {

    private StudentMapper() {}

    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setRaNumber(student.getRaNumber());
        if (student.getCourse() != null) {
            dto.setCourseId(student.getCourse().getId());
        }
        return dto;
    }

    public static Student toEntity(StudentDTO dto, Course course) {
        Student student = new Student();
        updateEntityFromDto(student, dto, course);
        return student;
    }

    public static void updateEntityFromDto(Student student, StudentDTO dto, Course course) {
        student.setName(dto.getName());
        student.setRaNumber(dto.getRaNumber());
        student.setCourse(course);
    }
}
