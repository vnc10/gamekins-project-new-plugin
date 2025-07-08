package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Student;
import gamekins.project.domain.dto.StudentDTO;
import gamekins.project.mapper.StudentMapper;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id).map(StudentMapper::toDTO);
    }

    public StudentDTO create(StudentDTO studentDTO) {
        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = StudentMapper.toEntity(studentDTO, course);
        student = studentRepository.save(student);
        return StudentMapper.toDTO(student);
    }

    public Optional<StudentDTO> update(Long id, StudentDTO studentDTO) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    Course course = courseRepository.findById(studentDTO.getCourseId())
                            .orElseThrow(() -> new RuntimeException("Course not found"));

                    StudentMapper.updateEntityFromDto(existingStudent, studentDTO, course);
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return StudentMapper.toDTO(updatedStudent);
                });
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
