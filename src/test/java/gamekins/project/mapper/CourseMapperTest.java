package gamekins.project.mapper;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseMapperTest {


    @Test
    void shouldCorrectlyMapCourseEntityToCourseDTO() {

        Course courseEntity = new Course();
        courseEntity.setId(1L);
        courseEntity.setName("Engenharia de Software");
        courseEntity.setCode("ES-01");

        CourseDTO resultDTO = CourseMapper.toDTO(courseEntity);

        assertNotNull(resultDTO);

        assertEquals(courseEntity.getId(), resultDTO.getId());
        assertEquals(courseEntity.getName(), resultDTO.getName());
        assertEquals(courseEntity.getCode(), resultDTO.getCode());
    }
}
