package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.mapper.SubjectMapper;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void shouldUpdateWhenIdExists() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Curso de teste");
        course.setCode("cdt-001");

        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Disciplina de teste");
        subject.setCode("ddt-001");
        subject.setCourse(course);

        SubjectDTO updateDto = new SubjectDTO();
        updateDto.setId(subject.getId());
        updateDto.setName("Disciplina de teste atualizada");
        updateDto.setCode("ddt-002");
        updateDto.setCourseId(course.getId());

        Subject subjectWithUpdates = new Subject();
        subjectWithUpdates.setId(subject.getId());
        subjectWithUpdates.setName(updateDto.getName());
        subjectWithUpdates.setCode(updateDto.getCode());
        subjectWithUpdates.setCourse(subject.getCourse());

        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subjectWithUpdates);

        Optional<SubjectDTO> response = subjectService.update(subject.getId(), updateDto);

        SubjectDTO updatedSubject = response.get();

        verify(subjectRepository).findById(subject.getId());
        verify(courseRepository).findById(course.getId());
        verify(subjectRepository).save(any(Subject.class));

        assertEquals(updatedSubject.getId(), updateDto.getId());
        assertEquals(updatedSubject.getName(), updateDto.getName());
        assertEquals(updatedSubject.getCode(), updateDto.getCode());
        assertEquals(updatedSubject.getCourseId(), updateDto.getCourseId());
    }

}