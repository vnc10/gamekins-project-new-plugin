package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.integration.MySQLTestContainer;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
class SubjectServiceIntegrationTest extends MySQLTestContainer {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectService subjectService;

    @Test
    void shouldDeleteById() {
        Course course = new Course();
        course.setName("Curso de Teste");
        course.setCode("code");
        course = courseRepository.save(course);

        Subject subject = new Subject();
        subject.setName("Subject");
        subject.setCode("code");
        subject.setCourse(course);
        Long id = subjectRepository.save(subject).getId();
        Assertions.assertTrue(subjectRepository.existsById(id));
        subjectService.deleteById(id);
        Assertions.assertFalse(subjectRepository.existsById(id));
    }
}
