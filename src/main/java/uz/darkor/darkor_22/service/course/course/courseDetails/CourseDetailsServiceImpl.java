package uz.darkor.darkor_22.service.course.course.courseDetails;

import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.course.CourseDetailCriteria;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeLocalizedDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailCreateDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailLocalizationDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailUpdateDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.entity.auth.Employee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.entity.course.CourseDetail;
import uz.darkor.darkor_22.entity.course.Price;
import uz.darkor.darkor_22.entity.system.Gallery;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.mapper.course.CourseDetailsMapper;
import uz.darkor.darkor_22.mapper.price.PriceMapper;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.repository.course.detail.CourseDetailRepository;
import uz.darkor.darkor_22.repository.employee.EmployeeRepository;
import uz.darkor.darkor_22.repository.price.PriceRepository;
import uz.darkor.darkor_22.repository.system.file.FileRepository;
import uz.darkor.darkor_22.service.AbstractService;
import uz.darkor.darkor_22.service.course.course.CourseDetailsService;
import uz.darkor.darkor_22.service.course.course.CourseServiceImpl;
import uz.darkor.darkor_22.service.course.price.PriceServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseDetailsServiceImpl extends AbstractService<CourseDetailsMapper, CourseDetailRepository> implements CourseDetailsService {
    private final FileRepository fileRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    private final PriceServiceImpl priceService;
    private final CourseServiceImpl courseService;
    public CourseDetailsServiceImpl(CourseDetailsMapper mapper, CourseDetailRepository repository, FileRepository fileRepository, EmployeeRepository employeeRepository, CourseRepository courseRepository, PriceRepository priceRepository, PriceMapper priceMapper, PriceServiceImpl priceService, CourseServiceImpl courseService) {
        super(mapper, repository);
        this.fileRepository = fileRepository;
        this.employeeRepository = employeeRepository;
        this.courseRepository = courseRepository;
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.priceService = priceService;
        this.courseService = courseService;
    }

    @Override
    public CourseDetailLocalizationDTO create(CourseDetailCreateDTO DTO) {
        CourseDetail courseDetail = mapper.fromCreateDTO(DTO);
        courseDetail.setFileUz(processGallery(DTO.getFileUz()));
        courseDetail.setFileRu(processGallery(DTO.getFileRu()));
        courseDetail.setFileEn(processGallery(DTO.getFileEn()));
        courseDetail.setPrice(priceService.createForEveryService(DTO.getPrice()));
        courseDetail.setCourse(courseService.createForEveryService(DTO.getCourse()));
        courseDetail.setYoutubeVideo(DTO.getYoutubeVideo());
        CourseDetail save = repository.save(courseDetail);
        return mapper.toGetDTO(save).getLocalizationDto("uz");
    }

    @Override
    public CourseDetailLocalizationDTO update(CourseDetailUpdateDTO DTO) {
        Optional<CourseDetail> byId = repository.findById(DTO.getId());
        if(byId.isEmpty()) throw new NotFoundException("Course Notfound");
        CourseDetail courseDetail = mapper.fromUpdateDTO(DTO, byId.get());
        courseDetail.setFileUz(processGallery(DTO.getFileUz()));
        courseDetail.setFileRu(processGallery(DTO.getFileRu()));
        courseDetail.setFileEn(processGallery(DTO.getFileEn()));
        courseDetail.setYoutubeVideo(DTO.getYoutubeVideo());
        Optional<Price> byId1 = priceRepository.findById(DTO.getPrice().getId());
        if (byId1.isEmpty()) throw new NotFoundException("Price not found");
        priceRepository.save(priceMapper.fromUpdateDTO(DTO.getPrice(), byId1.get()));
        courseDetail.setCourse(courseService.updateForEveryService(DTO.getCourse()));
        CourseDetail save = repository.save(courseDetail);
        return mapper.toGetDTO(save).getLocalizationDto("uz");
    }

    @Override
    public Boolean delete(Long key) {
        Optional<Course> byId = courseRepository.findById(key);
        if (byId.isEmpty()) throw new NotFoundException("Course Not Found");
        byId.get().setDeleted(true);
        courseRepository.save(byId.get());
        return true;
    }

    public CourseDetailLocalizationDTO get(CourseGetDTO courseDto, String language) {
        CourseDetail byCourse = repository.findByCourseId(courseDto.getId());
        return mapper.toGetDTO(byCourse).getLocalizationDto(language);
    }

    public CourseDetailUpdateDTO getForUpdate(Long id, String language){
        Optional<CourseDetail> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("COURSE_DETAIL_NOT_FOUND");
        CourseDetailUpdateDTO courseDetailUpdateDTO = mapper.toUpdateDTO(byId.get());
        courseDetailUpdateDTO.setCourse(courseService.getForUpdate(byId.get().getCourse().getId()));
        return courseDetailUpdateDTO;
    }

    @Override
    public CourseDetailLocalizationDTO get(Long key, String language) {
        Optional<CourseDetail> byCourse = repository.findById(key);
        if (byCourse.isEmpty()) throw new NotFoundException("course detail not found");
        return mapper.toGetDTO(byCourse.get()).getLocalizationDto(language);
    }

    @Override
    public List<CourseDetailLocalizationDTO> list(CourseDetailCriteria criteria, String language) {
        return null;
    }


    /* Yordamchi metodlar */

    private List<Gallery> processGallery(List<FileDTO> fileDTOS) {
        List<Gallery> galleries = new ArrayList<>();
        for (FileDTO fileDTO : fileDTOS) {
            Optional<Gallery> gallery = fileRepository.findById(fileDTO.getId());
            galleries.add(gallery.get());
        }
        return galleries;
    }

    private List<Employee> processEmployee(List<EmployeeLocalizedDTO> employeesDTO) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeLocalizedDTO employeeDto : employeesDTO) {
            Optional<Employee> skill = employeeRepository.findById(employeeDto.getId());
            employees.add(skill.get());
        }
        return employees;
    }

}
