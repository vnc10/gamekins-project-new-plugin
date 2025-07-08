package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;
import gamekins.project.mapper.CourseMapper;
import gamekins.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> findById(Long id) {
        return courseRepository.findById(id).map(CourseMapper::toDTO);
    }

    public CourseDTO create(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        try {
            course = courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Course creation failed");
        }
        return CourseMapper.toDTO(course);
    }

    public Optional<CourseDTO> update(Long id, CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    CourseMapper.updateEntityFromDto(existingCourse, courseDTO);
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return CourseMapper.toDTO(updatedCourse);
                });
    }

    public void deleteById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }
}
