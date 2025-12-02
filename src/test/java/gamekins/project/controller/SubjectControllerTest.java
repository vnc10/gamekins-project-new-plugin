package gamekins.project.controller;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import gamekins.project.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void shouldGetAllSubjects() {
        Course course = new Course();
        course.setId(1L);

        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Arquitetura");
        subject1.setCode("BCC2002");
        subject1.setCourse(course);

        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Arquitetura2.0");
        subject2.setCode("BCC2003");
        subject2.setCourse(course);

        List<Subject> subjects = Arrays.asList(subject1, subject2);

        when(subjectRepository.findAll()).thenReturn(subjects);

        List<SubjectDTO> result = subjectService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Arquitetura", result.get(0).getName());
        assertEquals("BCC2002", result.get(0).getCode());
        assertEquals("Arquitetura2.0", result.get(1).getName());
        assertEquals("BCC2003", result.get(1).getCode());

        verify(subjectRepository).findAll();
    }
}