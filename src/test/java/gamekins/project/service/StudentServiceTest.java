package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Student;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.StudentDTO;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.StudentRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldThrowWhenUpdateWithCourseNotExists() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Curso de teste");
        course.setCode("cdt-001");

        Student student = new Student();
        student.setId(1L);
        student.setName("Aluno de teste");
        student.setRaNumber("123456");
        student.setCourse(course);

        StudentDTO updateDto = new StudentDTO();
        updateDto.setId(student.getId());
        updateDto.setName("Aluno de teste atualizado");
        updateDto.setRaNumber("123456789");
        updateDto.setCourseId(2L);

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        assertThrows(RuntimeException.class, () -> { studentService.update(student.getId(), updateDto); });
    }

}