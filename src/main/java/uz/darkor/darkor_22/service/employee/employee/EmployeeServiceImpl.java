package uz.darkor.darkor_22.service.employee.employee;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.employee.EmployeeCriteria;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeCreateDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeLocalizedDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeUpdateDTO;
import uz.darkor.darkor_22.dto.auth.employee_detail.EmployeeDetailCreateDTO;
import uz.darkor.darkor_22.dto.auth.employee_with_detail.EmployeeWithDetailCreatDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillLocalizedDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.entity.auth.Employee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.enums.ContentType;
import uz.darkor.darkor_22.enums.EmployeeType;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.mapper.employee.EmployeeMapper;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.repository.employee.EmployeeRepository;
import uz.darkor.darkor_22.repository.system.file.FileRepository;
import uz.darkor.darkor_22.service.AbstractService;
import uz.darkor.darkor_22.service.employee.detail.EmployeeDetailService;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EmployeeServiceImpl extends AbstractService<EmployeeMapper, EmployeeRepository>
        implements EmployeeService {

    private final FileRepository fileRepository;
    private final CourseRepository courseRepository;
    private final EmployeeDetailService employeeDetailService;


    public EmployeeServiceImpl(EmployeeMapper mapper,
                               EmployeeRepository repository,
                               FileRepository fileRepository,
                               CourseRepository courseRepository,
                               @Lazy EmployeeDetailService employeeDetailService) {
        super(mapper, repository);
        this.fileRepository = fileRepository;
        this.courseRepository = courseRepository;
        this.employeeDetailService = employeeDetailService;
    }

    @Override
    public EmployeeLocalizedDTO createWithDetail(EmployeeWithDetailCreatDTO dto) {
//        checkType(dto.getType());
        EmployeeCreateDTO employeeCreateDTO = fromEmployeeWithDetailDTO(dto);
        Employee employee = fromCreateDTO(employeeCreateDTO);
        Employee newEmployee = repository.save(employee);
        EmployeeLocalizedDTO localizationDto = mapper.toGetDTO(newEmployee).getLocalizationDto();
        EmployeeDetailCreateDTO employeeDetailCreateDTO = fromEmployeeWithDetailDTO(dto, localizationDto);
        employeeDetailService.create(employeeDetailCreateDTO);
        return localizationDto;
    }

    @Override
    public EmployeeLocalizedDTO create(EmployeeCreateDTO DTO) {
        checkType(DTO.getType());
        Employee employee = fromCreateDTO(DTO);
        Employee save = repository.save(employee);
        return mapper.toGetDTO(save).getLocalizationDto();
    }


    @Override
    public EmployeeLocalizedDTO update(EmployeeUpdateDTO DTO) {
        checkType(DTO.getType());
        Employee target = checkExistenceAndGetByCode(DTO.getCode());
        Employee employee = mapper.fromUpdateDTO(DTO, target);
        Employee updatedEmployee = repository.save(employee);
        return mapper.toGetDTO(updatedEmployee).getLocalizationDto();
    }

    @Override
    @Transactional
    public Boolean delete(UUID key) {
        Employee employee = checkExistenceAndGetByCode(key);
        repository.delete(employee);
        return Boolean.TRUE;
    }

    public Boolean deleteMy(Long id) {
        Optional<Employee> byId = repository.findById(id);
        if  (byId.isEmpty()) throw new NotFoundException("EXPERT_NOT_FOUND");
        repository.delete(byId.get());
        return Boolean.TRUE;
    }

    @Override
    public EmployeeLocalizedDTO get(UUID key, String language) {
        Employee employee = checkExistenceAndGetByCode(key);
        return mapper.toGetDTO(employee).getLocalizationDto();
    }

    @Override
    public List<EmployeeLocalizedDTO> list(EmployeeCriteria criteria, String language) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Employee> employees = processResponseByCriteria(criteria);
        Page<Employee> response = new PageImpl<>(employees, pageable, repository.count());
        return getLocalizedDtos(response.getContent());
    }

    private List<Employee> processResponseByCriteria(EmployeeCriteria criteria) {
        List<Employee> employees;
        if (Objects.isNull(criteria.getType()) && Objects.isNull(criteria.getCourseId())) {
            employees = repository.findAll();
        } else if (Objects.nonNull(criteria.getType()) && Objects.nonNull(criteria.getCourseId())) {
            employees = repository.findAllByCoursesAndType(criteria.getCourseId(), criteria.getType().toUpperCase());
        } else if (Objects.isNull(criteria.getType()) && Objects.nonNull(criteria.getCourseId())) {
            employees = repository.findAllByCoursesAny(criteria.getCourseId());
        } else {
            employees = repository.findAllByType(EmployeeType.valueOf(criteria.getType().toUpperCase()));
        }
        return employees;
    }


    public List<EmployeeLocalizedDTO> getAllByCourseCode(EmployeeCriteria criteria, UUID courseCode) {
        Course course = courseRepository.findByCode(courseCode);
        if (Objects.isNull(course)) throw new NotFoundException("COURSE_NOT_FOUND");
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Employee> list = repository.findAllByCourses(course.getId());
        Page<Employee> employees = new PageImpl<>(list, pageable, repository.count());
        return getLocalizedDtos(employees.getContent());
    }


    private EmployeeDetailCreateDTO fromEmployeeWithDetailDTO(EmployeeWithDetailCreatDTO dto,
                                                              EmployeeLocalizedDTO localizationDto) {
        return
                new EmployeeDetailCreateDTO(
                        dto.getTitleDescriptionUz(),
                        dto.getTitleDescriptionRu(),
                        dto.getTitleDescriptionEn(),
                        dto.getBodyDescriptionUz(),
                        dto.getBodyDescriptionRu(),
                        dto.getBodyDescriptionEn(),
                        dto.getYouTubeVideo(),
                        dto.getGalleries(),
                        localizationDto
                );
    }

    private EmployeeCreateDTO fromEmployeeWithDetailDTO(EmployeeWithDetailCreatDTO dto) {
        return new EmployeeCreateDTO(
                dto.getFullNameUz(),
                dto.getFullNameRu(),
                dto.getFullNameEn(),
                dto.getType(),
                getProfilePhoto(dto.getGalleries()),
                dto.getCourses()
        );
    }

    private FileDTO getProfilePhoto(List<FileDTO> fileDTOS) {
        for (FileDTO fileDTO : fileDTOS) {
            if (fileDTO.getFileType() == ContentType.PROFILE)
                return fileDTO;
        }
        throw new RuntimeException("PROFILE_PHOTO_NOT_PROVIDED");
    }


    private List<Course> getCourses(List<CourseLocalizationDTO> DTOs) {
        List<Course> courses = new ArrayList<>();
        for (CourseLocalizationDTO course : DTOs) {
            Course byCode = courseRepository.findById(course.getId()).orElseThrow(() -> new NotFoundException("Coures not found"));
            courses.add(byCode);
        }
        return courses;
    }


    private Employee fromCreateDTO(EmployeeCreateDTO DTO) {
        return new Employee(DTO.getFullNameUz(),
                DTO.getFullNameRu(),
                DTO.getFullNameEn(),
//                EmployeeType.valueOf(DTO.getType()),
                Enum.valueOf(EmployeeType.class, DTO.getType()),
                fileRepository.findAllByCode(DTO.getGallery().getCode())
                        .orElseThrow(() -> new NotFoundException("GALLERY_NOT_FOUND")),
                getCourses(DTO.getCourses())
        );
    }

    private List<EmployeeLocalizedDTO> getLocalizedDtos(List<Employee> employees) {
        List<EmployeeLocalizedDTO> employeeDtos = new ArrayList<>();
        for (Employee e : employees) {
            employeeDtos.add(mapper.toGetDTO(e).getLocalizationDto());
        }

        for (int i = 0; i <= employeeDtos.toArray().length-1; i++) {
            for (int j = i+1; j <= employeeDtos.toArray().length-1; j++) {
                if (employeeDtos.get(i).getId().equals(employeeDtos.get(j).getId()) && i!=j) {
                    employeeDtos.remove(j);
                }
            }
        }

        return employeeDtos;
    }


    private void checkType(String type) {
        if (type.toUpperCase().equals(EmployeeType.EXPERT.toString())
                || type.toUpperCase().equals(EmployeeType.STUDENT.toString())) {
            return;
        }
        throw new RuntimeException("INVALID_EMPLOYEE_TYPE");
    }


    public Employee checkExistenceAndGetByCode(UUID code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("EMPLOYEE_NOT_FOUND"));

    }

}
