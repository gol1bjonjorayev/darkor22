package uz.darkor.darkor_22.service.auth.userEmployee;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.auth.userEmployee.UserEmployeeCriteria;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeUpdateDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.exception.validator.BadRequestException;
import uz.darkor.darkor_22.mapper.auth.userEmployee.UserEmployeeMapper;
import uz.darkor.darkor_22.mapper.course.CourseMapper;
import uz.darkor.darkor_22.repository.auth.userEmployee.UserEmployeeRepository;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserEmployeeService extends AbstractService<UserEmployeeMapper, UserEmployeeRepository> {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public UserEmployeeService(UserEmployeeMapper mapper, UserEmployeeRepository repository, CourseRepository courseRepository, CourseMapper courseMapper) {
        super(mapper, repository);
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public List<UserEmployeeGetDto> getAllByCourse(UserEmployeeCriteria criteria, Long id, String lang) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("COURSE_NOT_FOUND");
        List<UserEmployee> allByCourses = repository.findAllByCoursesAndAccessTrue(byId.get(), Sort.by(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<UserEmployee> employees = new PageImpl<>(allByCourses, pageable, repository.count());
        return courseGetLocalized(employees.getContent(),lang);
    }

    public List<UserEmployeeGetDto> getAllForAdmin(UserEmployeeCriteria criteria, String lang) {
        List<UserEmployee> allByCourses = repository.findAllByOrderByIdDesc();
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<UserEmployee> employees = new PageImpl<>(allByCourses, pageable, repository.count());
        return courseGetLocalized(employees.getContent(),lang);
    }

    public Boolean givePermission(Long id, Boolean access){
        Optional<UserEmployee> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("STUDENT_NOT_FOUND");
        UserEmployee userEmployee = byId.get();
        userEmployee.setAccess(access);
        repository.save(userEmployee);
        return true;
    }

    public UserEmployee create(UserEmployeeCreateDto dto) {
        UserEmployee userEmployee = mapper.fromCreateDTO(dto);
        List<Course> courseList = new ArrayList<>();
        for (Long id : dto.getCourseIds()) {
            Optional<Course> byId = courseRepository.findById(id);
            courseList.add(byId.get());
        }
        userEmployee.setCourses(courseList);
        UserEmployee save = repository.save(userEmployee);
        return save;
    }

    public UserEmployee update(UserEmployeeUpdateDto dto) {
        Optional<UserEmployee> byId = repository.findById(dto.getId());
        if (byId.isEmpty()) throw new NotFoundException("USER_EMPLOYEE_NOT_FOUND");
        UserEmployee userEmployee = mapper.fromUpdateDTO(dto, byId.get());
        UserEmployee save = repository.save(userEmployee);
        if (Objects.isNull(save)) throw new BadRequestException("Can't update");
        return save;
    }



    public List<UserEmployeeGetDto> courseGetLocalized(List<UserEmployee> employees, String lang) {
        List<UserEmployeeGetDto> getDto = new ArrayList<>();
        for (UserEmployee userEmployee : employees) {
            List<CourseLocalizationDTO> courseLocalizedDtos = new ArrayList<>();
            for (Course course : userEmployee.getCourses()) {
                courseLocalizedDtos.add(courseMapper.toGetDTO(course).getLocalizationDto(lang));
            }
            UserEmployeeGetDto userEmployeeGetDto = mapper.toGetDTO(userEmployee);
            userEmployeeGetDto.setCourses(courseLocalizedDtos);
            getDto.add(userEmployeeGetDto);
        }
        return getDto;
    }

}
