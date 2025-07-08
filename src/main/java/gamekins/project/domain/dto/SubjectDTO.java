package gamekins.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {
    private Long id;
    private String name;
    private String code;
    private Long courseId;
}
