package gamekins.project.mapper;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
//    Exemplo de teste parametrizado
//    @ParameterizedTest
//    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
//    void shouldCorrectlyMapCourseEntityToCourseDTOWithManyParameters(int number) {
//
//        Course courseEntity = new Course();
//        courseEntity.setId((long) number);
//        courseEntity.setName("Engenharia de Software");
//        courseEntity.setCode("ES-01");
//
//        CourseDTO resultDTO = CourseMapper.toDTO(courseEntity);
//
//        assertNotNull(resultDTO);
//
//        assertEquals(courseEntity.getId(), resultDTO.getId());
//        assertEquals(courseEntity.getName(), resultDTO.getName());
//        assertEquals(courseEntity.getCode(), resultDTO.getCode());
//    }


}
