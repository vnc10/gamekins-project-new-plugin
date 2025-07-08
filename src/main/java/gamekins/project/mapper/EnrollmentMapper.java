package gamekins.project.mapper;

import gamekins.project.domain.Enrollment;
import gamekins.project.domain.Student;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.EnrollmentDTO;

public final class EnrollmentMapper {

    private EnrollmentMapper() {}

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        if (enrollment.getStudent() != null) {
            dto.setStudentId(enrollment.getStudent().getId());
        }
        if (enrollment.getSubject() != null) {
            dto.setSubjectId(enrollment.getSubject().getId());
        }
        return dto;
    }

    public static Enrollment toEntity(Student student, Subject subject) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        return enrollment;
    }
}
