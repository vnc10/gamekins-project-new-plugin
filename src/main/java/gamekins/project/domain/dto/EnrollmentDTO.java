package gamekins.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentDTO {
    private Long id;
    private LocalDateTime enrollmentDate;
    private String status;
    private Long studentId;
    private Long subjectId;
}
