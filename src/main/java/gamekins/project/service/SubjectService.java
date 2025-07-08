package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.mapper.SubjectMapper;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubjectDTO> findById(Long id) {
        return subjectRepository.findById(id).map(SubjectMapper::toDTO);
    }

    public SubjectDTO create(SubjectDTO subjectDTO) {

        Course course = courseRepository.findById(subjectDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + subjectDTO.getCourseId()));

        Subject subject = SubjectMapper.toEntity(subjectDTO, course);

        subject = subjectRepository.save(subject);
        return SubjectMapper.toDTO(subject);
    }

    public Optional<SubjectDTO> update(Long id, SubjectDTO subjectDTO) {
        return subjectRepository.findById(id)
                .map(existingSubject -> {
                    Course course = courseRepository.findById(subjectDTO.getCourseId())
                            .orElseThrow(() -> new RuntimeException("Course not found with id: " + subjectDTO.getCourseId()));

                    SubjectMapper.updateEntityFromDto(existingSubject, subjectDTO, course);
                    Subject updatedSubject = subjectRepository.save(existingSubject);
                    return SubjectMapper.toDTO(updatedSubject);
                });
    }

    public void deleteById(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        }
    }
}
