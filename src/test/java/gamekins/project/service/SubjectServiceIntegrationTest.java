package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.integration.MySQLTestContainer;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class SubjectServiceIntegrationTest extends MySQLTestContainer {

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @AfterEach
  void tearDown() {
    subjectRepository.deleteAll();
    courseRepository.deleteAll();
  }

  @Test
  void shouldCreateSubjectSuccessfully() {
    // Arrange
    Course course = new Course();
    course.setName("Computer Science Create Test");
    course.setCode("CS-" + UUID.randomUUID().toString());
    course = courseRepository.save(course);

    SubjectDTO subjectDTO = new SubjectDTO();
    subjectDTO.setName("Algorithms");
    subjectDTO.setCode("ALG-" + UUID.randomUUID().toString());
    subjectDTO.setCourseId(course.getId());

    // Act
    SubjectDTO result = subjectService.create(subjectDTO);

    // Assert
    assertNotNull(result.getId(), "O ID não deveria ser nulo após a criação");
    assertEquals(subjectDTO.getName(), result.getName());
    assertEquals(subjectDTO.getCode(), result.getCode());
    assertEquals(course.getId(), result.getCourseId());

    Optional<Subject> savedSubject = subjectRepository.findById(result.getId());
    assertTrue(savedSubject.isPresent(), "A disciplina deveria ter sido salva no banco de dados");
    assertEquals(subjectDTO.getName(), savedSubject.get().getName());
  }

  @Test
  void shouldFindSubjectById() {
    // Arrange
    Course course = new Course();
    course.setName("Mathematics Find Test");
    course.setCode("MAT-" + UUID.randomUUID().toString());
    course = courseRepository.save(course);

    Subject subject = new Subject();
    subject.setName("Calculus I");
    subject.setCode("CALC-" + UUID.randomUUID().toString());
    subject.setCourse(course);
    subject = subjectRepository.save(subject);

    // Act
    Optional<SubjectDTO> result = subjectService.findById(subject.getId());

    // Assert
    assertTrue(result.isPresent(), "A disciplina deveria ser encontrada pelo ID");
    assertEquals(subject.getId(), result.get().getId());
    assertEquals(subject.getName(), result.get().getName());
    assertEquals(subject.getCode(), result.get().getCode());
    assertEquals(course.getId(), result.get().getCourseId());
  }
}
