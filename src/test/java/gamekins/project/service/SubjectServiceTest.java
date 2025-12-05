package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void shouldCreateSubject() {
        Course course = new Course();
        course.setName("Curso de Teste");
        course.setCode("code");

        Mockito.when(courseRepository.findById(1L))
                .thenReturn(Optional.of(course));

        Subject savedSubject = new Subject();
        savedSubject.setId(10L);
        savedSubject.setName("name");
        savedSubject.setCode("code");
        savedSubject.setCourse(course);

        Mockito.when(subjectRepository.save(Mockito.any(Subject.class)))
                .thenReturn(savedSubject);

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setCourseId(1L);
        subjectDTO.setCode("code");
        subjectDTO.setName("name");

        SubjectDTO response = subjectService.create(subjectDTO);

        Assertions.assertEquals(10L, response.getId());
        Assertions.assertEquals("name", response.getName());
        Assertions.assertEquals("code", response.getCode());

        Mockito.verify(subjectRepository).save(Mockito.any(Subject.class));
    }
}
