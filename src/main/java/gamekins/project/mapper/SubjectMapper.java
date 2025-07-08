package gamekins.project.mapper;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;

public final class SubjectMapper {

    private SubjectMapper() {}

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setCode(subject.getCode());
        if (subject.getCourse() != null) {
            dto.setCourseId(subject.getCourse().getId());
        }
        return dto;
    }

    public static Subject toEntity(SubjectDTO dto, Course course) {
        Subject subject = new Subject();
        updateEntityFromDto(subject, dto, course);
        return subject;
    }

    public static void updateEntityFromDto(Subject subject, SubjectDTO dto, Course course) {
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setCourse(course);
    }
}