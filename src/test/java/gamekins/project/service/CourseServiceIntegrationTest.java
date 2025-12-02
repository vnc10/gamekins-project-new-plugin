package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.integration.MySQLTestContainer;
import gamekins.project.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class CourseServiceIntegrationTest extends MySQLTestContainer {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldDeleteCourseSuccessfully() {
        Course course = new Course();
        course.setName("Curso de Teste");
        course.setCode("312");
        course = courseRepository.save(course);
        Long id = course.getId();

        assertTrue(courseRepository.existsById(id), "O curso deveria existir antes do teste de delete");

        courseService.deleteById(id);

        boolean exists = courseRepository.existsById(id);
        assertFalse(exists, "O curso deveria ter sido deletado do banco de dados");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void shouldDeleteCourseSuccessfullyOnParameterized(int number) {
        Course course = new Course();
        course.setName("Curso de Teste " + number);
        course.setCode("CODE-" + number);
        course = courseRepository.save(course);
        Long id = course.getId();

        assertTrue(courseRepository.existsById(id), "O curso deveria existir antes do teste de delete");

        courseService.deleteById(id);

        boolean exists = courseRepository.existsById(id);
        assertFalse(exists, "O curso deveria ter sido deletado do banco de dados");
    }
}
