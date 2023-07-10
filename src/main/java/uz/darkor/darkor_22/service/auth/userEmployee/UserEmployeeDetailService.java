package uz.darkor.darkor_22.service.auth.userEmployee;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailForAdminDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailGetDto;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.entity.auth.UserEmployeeDetail;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.exception.validator.BadRequestException;
import uz.darkor.darkor_22.mapper.auth.userEmployee.UserEmployeeDetailMapper;
import uz.darkor.darkor_22.mapper.auth.userEmployee.UserEmployeeMapper;
import uz.darkor.darkor_22.mapper.course.CourseMapper;
import uz.darkor.darkor_22.repository.auth.userEmployee.UserEmployeeDetailRepository;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEmployeeDetailService extends AbstractService<UserEmployeeDetailMapper, UserEmployeeDetailRepository> {

    private final CourseRepository courseRepository;
    private final UserEmployeeService userEmployeeService;
    private final CourseMapper courseMapper;
    private final UserEmployeeMapper uem;

    public UserEmployeeDetailService(UserEmployeeDetailMapper mapper, UserEmployeeDetailRepository repository, CourseRepository courseRepository, UserEmployeeService userEmployeeService, CourseMapper courseMapper, UserEmployeeMapper uem) {
        super(mapper, repository);
        this.courseRepository = courseRepository;
        this.userEmployeeService = userEmployeeService;
        this.courseMapper = courseMapper;
        this.uem = uem;
    }


    public UserEmployeeDetailGetDto getDetail(Long id, String lang){
        Optional<UserEmployeeDetail> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("DETAIL_NOT_FOUND");
        UserEmployeeDetailGetDto userEmployeeDetailGetDto = mapper.toGetDTO(byId.get());
        userEmployeeDetailGetDto.setUserEmployee(courseGetLocalizedWithUserEmployee(byId.get().getUserEmployee(), lang));
        return userEmployeeDetailGetDto;
    }

    public UserEmployeeDetailForAdminDto getDetailForAdmin(Long id, String lang){
        Optional<UserEmployeeDetail> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("DETAIL_NOT_FOUND");
        UserEmployeeDetailForAdminDto userEmployeeDetailGetDto = mapper.toGetForAdminDTO(byId.get());
        userEmployeeDetailGetDto.setUserEmployee(courseGetLocalizedWithUserEmployee(byId.get().getUserEmployee(), lang));
        return userEmployeeDetailGetDto;
    }

    public UserEmployeeDetailGetDto create(UserEmployeeDetailCreateDto dto, String lang){
        UserEmployeeDetail userEmployeeDetail = mapper.fromCreateDTO(dto);
        UserEmployee userEmployee = userEmployeeService.create(dto.getUserEmployee());
        userEmployeeDetail.setUserEmployee(userEmployee);
        UserEmployeeDetail save = repository.save(userEmployeeDetail);
        UserEmployeeDetailGetDto userEmployeeDetailGetDto = mapper.toGetDTO(save);
        List<CourseLocalizationDTO> courseLocalizationDTOS = courseLocalizationDTOS(userEmployee.getCourses(), lang);
        UserEmployeeGetDto userEmployeeGetDto = uem.toGetDTO(userEmployee);
        userEmployeeGetDto.setCourses(courseLocalizationDTOS);
        userEmployeeDetailGetDto.setUserEmployee(userEmployeeGetDto);
        return userEmployeeDetailGetDto;
    }

    public Boolean delete(Long id){
        Optional<UserEmployeeDetail> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("User Employee Detail Not Found");
        repository.delete(byId.get());
        return true;
    }

    public List<CourseLocalizationDTO> courseLocalizationDTOS(List<Course> courses, String lang){
        List<CourseLocalizationDTO> localize = new ArrayList<>();
        for (Course course : courses) {
            CourseGetDTO courseGetDTO = courseMapper.toGetDTO(course);
            localize.add(courseGetDTO.getLocalizationDto(lang));
        }
        return localize;
    }


    public UserEmployeeGetDto courseGetLocalizedWithUserEmployee(UserEmployee employee, String lang) {
            List<CourseLocalizationDTO> courseLocalizedDtos = new ArrayList<>();
            for (Course course : employee.getCourses()) {
                courseLocalizedDtos.add(courseMapper.toGetDTO(course).getLocalizationDto(lang));
            }
            UserEmployeeGetDto userEmployeeGetDto = uem.toGetDTO(employee);
            userEmployeeGetDto.setCourses(courseLocalizedDtos);
        return userEmployeeGetDto;
    }


}
