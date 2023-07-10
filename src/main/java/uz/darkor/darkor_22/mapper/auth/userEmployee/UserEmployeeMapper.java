package uz.darkor.darkor_22.mapper.auth.userEmployee;

import org.mapstruct.Mapper;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeShowDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeUpdateDto;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.mapper.GenericMapper;
import uz.darkor.darkor_22.mapper.course.CourseMapper;
import uz.darkor.darkor_22.mapper.system.file.FileMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FileMapper.class, CourseMapper.class})
public interface UserEmployeeMapper extends GenericMapper<UserEmployeeCreateDto, UserEmployeeUpdateDto, UserEmployeeGetDto, UserEmployee> {

    @Override
    UserEmployee fromCreateDTO(UserEmployeeCreateDto createDTO);

//    @Override
//    UserEmployee fromUpdateDTO(UserEmployeeUpdateDto updateDTO, UserEmployee entity);

    @Override
    UserEmployeeGetDto toGetDTO(UserEmployee entity);

    @Override
    List<UserEmployeeGetDto> toListDTO(List<UserEmployee> entities);

    UserEmployeeShowDto toShowDTO(UserEmployee entities);
}
