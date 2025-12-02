package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.CourseDTO;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.mapper.SubjectMapper;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void shouldCreateSubject() {
        Course course = new Course();
        course.setId(1L);

        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Teste");
        subject.setCode("001");
        subject.setCourse(course);

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Teste");
        subjectDTO.setCode("001");
        subjectDTO.setCourseId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        SubjectDTO result = subjectService.create(subjectDTO);

        assertNotNull(result);
        assertEquals("Teste", result.getName());
        assertEquals("001", result.getCode());
        assertEquals(1L, result.getCourseId());

        verify(courseRepository).findById(1L);
        verify(subjectRepository).save(any(Subject.class));
    }

    @Test
    void shouldFindByIdSuccess() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Teste1");
        subject.setCode("001");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Optional<SubjectDTO> result = subjectService.findById(1L);

        assertNotNull(result);
        assertEquals("Teste1", result.get().getName());
    }
}