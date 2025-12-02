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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private SubjectController subjectController;

    @Test
    void shouldGetAllSubjects() {
        Course course = new Course();
        course.setId(1L);

        SubjectDTO subjectDTO1 = new SubjectDTO();
        subjectDTO1.setId(1L);
        subjectDTO1.setName("Arquitetura");
        subjectDTO1.setCode("BCC2002");

        SubjectDTO subjectDTO2 = new SubjectDTO();
        subjectDTO2.setId(2L);
        subjectDTO2.setName("Arquitetura2.0");
        subjectDTO2.setCode("BCC2003");

        List<SubjectDTO> subjects = Arrays.asList(subjectDTO1, subjectDTO2);

        when(subjectService.findAll()).thenReturn(subjects);

        List<SubjectDTO> result = subjectController.getAllSubjects();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Arquitetura", result.get(0).getName());
        assertEquals("BCC2002", result.get(0).getCode());
        assertEquals("Arquitetura2.0", result.get(1).getName());
        assertEquals("BCC2003", result.get(1).getCode());

        verify(subjectService).findAll();
    }
}