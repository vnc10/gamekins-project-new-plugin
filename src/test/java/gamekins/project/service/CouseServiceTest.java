package gamekins.project.service;

import gamekins.project.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}