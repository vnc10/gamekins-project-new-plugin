package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.integration.MySQLTestContainer;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Testcontainers
public class SubjectServiceIntegrationTest  extends MySQLTestContainer{
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void shouldFindSubjectByIdSuccessfully() {
        Course course = new Course();
        course.setName("Curso de teste");
        course.setCode("cdt-001");

        courseRepository.save(course);

        Subject subject = new Subject();
        subject.setName("Disciplina de teste");
        subject.setCode("ddt-001");
        subject.setCourse(course);

        subjectRepository.save(subject);

        assertNotNull(subjectService.findById(subject.getId()));
    }
}