package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Student;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldDeleteStudentSuccessfully() {
        Random gerador = new Random();
        String ra = String.format("%05d", gerador.nextInt(10000));
        Course course = new Course();
        course.setName("Curso de Teste");
        course.setCode(gerador.toString());
        course = courseRepository.save(course);
        Long courseId = course.getId();
        assertTrue(courseRepository.existsById(courseId), "O curso deveria existir antes do teste de delete");

        Student student = new Student();
        student.setCourse(course);
        student.setRaNumber(ra.toString());
        student.setName("Nome do Student");

        Long studentId = studentRepository.save(student).getId();
        studentService.deleteById(studentId);

        boolean exists = studentRepository.existsById(courseId);
        assertFalse(exists, "O Aluno deveria ter sido deletado do banco de dados");
        courseRepository.deleteById(course.getId());
    }
}
