package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;


    //Exemplo de teste usando Mock
    @Test
    void shouldDeleteWhenIdExists() {
        Long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);

        courseService.deleteById(id);

        verify(courseRepository).existsById(id);
        verify(courseRepository).deleteById(id);
    }

    @Test
    void shouldFindAllCourses() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Teste1");
        course1.setCode("001");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Teste2");
        course2.setCode("002");

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseDTO> result = courseService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Teste1", result.get(0).getName());
    }
}