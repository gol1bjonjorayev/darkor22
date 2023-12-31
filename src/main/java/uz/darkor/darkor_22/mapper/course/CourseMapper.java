package uz.darkor.darkor_22.mapper.course;

import org.mapstruct.Mapper;
import uz.darkor.darkor_22.dto.course.course.CourseCreateDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.course.course.CourseUpdateDTO;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.mapper.GenericMapper;
import uz.darkor.darkor_22.mapper.system.file.FileMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FileMapper.class})
public interface CourseMapper extends GenericMapper<CourseCreateDTO, CourseUpdateDTO, CourseGetDTO, Course> {

    CourseUpdateDTO toUpdateDTO(Course entity);

    @Override
    List<CourseGetDTO> toListDTO(List<Course> entities);
//    List<CourseGetDTO> toGetDTOMy(List<Course> dto);

    Course fromGetDto(CourseGetDTO dto);

    List<CourseLocalizationDTO> toLocalizationListDTO(List<CourseGetDTO> dto);

}
