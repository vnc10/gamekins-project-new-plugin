package gamekins.project.service;

import gamekins.project.domain.Enrollment;
import gamekins.project.domain.Student;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.EnrollmentDTO;
import gamekins.project.mapper.EnrollmentMapper;
import gamekins.project.repository.EnrollmentRepository;
import gamekins.project.repository.StudentRepository;
import gamekins.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<EnrollmentDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnrollmentDTO> findById(Long id) {
        return enrollmentRepository.findById(id).map(EnrollmentMapper::toDTO);
    }


    public EnrollmentDTO create(EnrollmentDTO enrollmentDTO) {

        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + enrollmentDTO.getStudentId()));
        Subject subject = subjectRepository.findById(enrollmentDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + enrollmentDTO.getSubjectId()));

        Enrollment enrollment = EnrollmentMapper.toEntity(student, subject);

        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toDTO(enrollment);
    }

    public void deleteById(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
        }
    }
}
