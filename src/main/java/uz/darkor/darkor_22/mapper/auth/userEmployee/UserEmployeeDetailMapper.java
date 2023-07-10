package uz.darkor.darkor_22.mapper.auth.userEmployee;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailForAdminDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailGetDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailUpdateDto;
import uz.darkor.darkor_22.entity.auth.UserEmployeeDetail;
import uz.darkor.darkor_22.mapper.GenericMapper;
import uz.darkor.darkor_22.mapper.course.CourseMapper;
import uz.darkor.darkor_22.mapper.system.file.FileMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {CourseMapper.class, FileMapper.class, UserEmployeeMapper.class})
public interface UserEmployeeDetailMapper extends GenericMapper<UserEmployeeDetailCreateDto, UserEmployeeDetailUpdateDto, UserEmployeeDetailGetDto, UserEmployeeDetail> {
    @Override
    UserEmployeeDetail fromCreateDTO(UserEmployeeDetailCreateDto createDTO);

    @Override
    UserEmployeeDetailGetDto toGetDTO(UserEmployeeDetail entity);

    UserEmployeeDetailForAdminDto toGetForAdminDTO(UserEmployeeDetail entity);

    @Override
    List<UserEmployeeDetailGetDto> toListDTO(List<UserEmployeeDetail> entities);
}
