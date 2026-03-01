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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class CourseServiceIntegrationTest extends MySQLTestContainer {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @ParameterizedTest
    @ValueSource(strings = {"code-001", "code-002", "code-003", "code-004"})
    void shouldDeleteCourseSuccessfully(String code) {
        Course course = new Course();
        course.setName("Curso de Teste");
        course.setCode(code);
        course = courseRepository.save(course);
        Long id = course.getId();

        assertTrue(courseRepository.existsById(id), "O curso deveria existir antes do teste de delete");

        courseService.deleteById(id);

        boolean exists = courseRepository.existsById(id);
        assertFalse(exists, "O curso deveria ter sido deletado do banco de dados");
    }
}
