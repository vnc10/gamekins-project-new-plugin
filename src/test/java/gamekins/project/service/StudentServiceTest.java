package gamekins.project.service;

import gamekins.project.domain.dto.StudentDTO;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private CourseRepository courseRepository;

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentService studentService;

  @Test
  void shouldThrowExceptionWhenCourseNotFoundOnCreate() {
    // Arrange
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setName("João");
    studentDTO.setRaNumber("123456");
    Long nonExistentCourseId = 99L;
    studentDTO.setCourseId(nonExistentCourseId);

    when(courseRepository.findById(nonExistentCourseId)).thenReturn(Optional.empty());

    // Act & Assert
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      studentService.create(studentDTO);
    });

    assertEquals("Course not found", exception.getMessage());
  }
}
