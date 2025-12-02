package gamekins.project.service;

import gamekins.project.domain.Course;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.mapper.SubjectMapper;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void shouldCreateSubject() {
        Course course = new Course();
        course.setId(1L);

        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Teste");
        subject.setCode("001");
        subject.setCourse(course);

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Teste");
        subjectDTO.setCode("001");
        subjectDTO.setCourseId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        SubjectDTO result = subjectService.create(subjectDTO);

        assertNotNull(result);
        assertEquals("Teste", result.getName());
        assertEquals("001", result.getCode());
        assertEquals(1L, result.getCourseId());

        verify(courseRepository).findById(1L);
        verify(subjectRepository).save(any(Subject.class));
    }

    @Test
    void shouldFindByIdSuccess() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Teste1");
        subject.setCode("001");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Optional<SubjectDTO> result = subjectService.findById(1L);

        assertNotNull(result);
        assertEquals("Teste1", result.get().getName());
    }

    @Test
    void shouldUpdateSubjectSuccess() {
        Subject existingSubject = new Subject();
        existingSubject.setId(1L);
        existingSubject.setName("Teste1");
        existingSubject.setCode("001");

        Course course = new Course();
        course.setId(1L);

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Teste 01");
        subjectDTO.setCode("002");
        subjectDTO.setCourseId(1L);

        Subject updatedSubject = new Subject();
        updatedSubject.setId(1L);
        updatedSubject.setName("Teste 01");
        updatedSubject.setCode("002");
        updatedSubject.setCourse(course);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(existingSubject));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(subjectRepository.save(any(Subject.class))).thenReturn(updatedSubject);

        Optional<SubjectDTO> result = subjectService.update(1L, subjectDTO);

        assertNotNull(result);
        assertEquals(true, result.isPresent());
        assertEquals("Teste 01", result.get().getName());
        assertEquals("002", result.get().getCode());
        assertEquals(1L, result.get().getCourseId());

        verify(subjectRepository).findById(1L);
        verify(courseRepository).findById(1L);
        verify(subjectRepository).save(any(Subject.class));
    }
}