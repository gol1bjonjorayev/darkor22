package uz.darkor.darkor_22.service.course.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.course.CourseCriteria;
import uz.darkor.darkor_22.dto.course.course.CourseCreateDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.course.course.CourseUpdateDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.entity.auth.Employee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.entity.system.Gallery;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.mapper.course.CourseMapper;
import uz.darkor.darkor_22.mapper.system.file.FileMapper;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.repository.system.file.FileRepository;
import uz.darkor.darkor_22.service.AbstractService;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CourseServiceImpl extends AbstractService<CourseMapper, CourseRepository> implements CourseService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public CourseServiceImpl(CourseMapper mapper,
                             CourseRepository repository,
                             FileRepository fileRepository,
                             FileMapper fileMapper) {
        super(mapper, repository);
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public CourseLocalizationDTO create(CourseCreateDTO DTO) {
        Course course = fromCreatDto(DTO);
        Course save = repository.save(course);
        return mapper.toGetDTO(save).getLocalizationDto("uz");

    }

    @Override
    public CourseLocalizationDTO update(CourseUpdateDTO DTO) {
        Optional<Course> courseOptional = repository.findById(DTO.getId());
        Course course = courseOptional.get();
        if (Objects.isNull(course)) {
            throw new NotFoundException("Course not found");
        }
        course = fromUpdateDto(DTO);
        return mapper.toGetDTO(repository.save(course)).getLocalizationDto("uz");
    }

    public Boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (NotFoundException e) {
            throw new NotFoundException("Notfound");
        }
    }

    @Override
    public CourseLocalizationDTO get(UUID key, String language) {
        return null;
    }


    public CourseLocalizationDTO getOne(Long id, String language) {
        Optional<Course> byId = repository.findById(id);
        if  (byId.isEmpty()) throw new NotFoundException("Course_Not_Found");
        CourseGetDTO courseGetDTO = mapper.toGetDTO(byId.get());
        return courseGetDTO.getLocalizationDto(language);
    }

    @Override
    public Boolean delete(UUID key) {
        return null;
    }


    @Override
    public List<CourseLocalizationDTO> list(CourseCriteria criteria, String language) {
        List<CourseLocalizationDTO> courseLocalizationDTOS = new ArrayList<>();
        PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Course> courses = repository.findAllAndDeletedFalse();
        Page<Course> response = new PageImpl<>(courses, pageable, repository.count());
        List<CourseGetDTO> courseGetDTOS = mapper.toListDTO(response.getContent());
        for (CourseGetDTO c : courseGetDTOS) {
            courseLocalizationDTOS.add(c.getLocalizationDto(language));
        }
        return courseLocalizationDTOS;
    }

    public List<CourseLocalizationDTO> listForFilter(String lang){
        List<CourseLocalizationDTO> courseLocalizationDTOS = new ArrayList<>();
        List<Course> courses = repository.findAll();
        List<CourseGetDTO> courseGetDTOS = mapper.toListDTO(courses);
        for (CourseGetDTO c : courseGetDTOS) {
            courseLocalizationDTOS.add(c.getLocalizationDto(lang));
        }
        return courseLocalizationDTOS;
    }



    /*Yordamchi metodlara*/

    private Course fromCreatDto(CourseCreateDTO DTO) {
        return new Course(
                DTO.getNameUz(),
                DTO.getNameRu(),
                DTO.getNameEn(),
                DTO.getDescriptionUz(),
                DTO.getDescriptionEn(),
                DTO.getDescriptionRu(),
                processGallery(DTO.getImageUz()),
                processGallery(DTO.getImageRu()),
                processGallery(DTO.getImageEn())
        );
    }

    private Course fromUpdateDto(CourseUpdateDTO DTO) {
        return new Course(
                DTO.getNameUz(),
                DTO.getNameRu(),
                DTO.getNameEn(),
                DTO.getDescriptionUz(),
                DTO.getDescriptionEn(),
                DTO.getDescriptionRu(),
                processGallery(DTO.getImageUz()),
                processGallery(DTO.getImageRu()),
                processGallery(DTO.getImageEn())
        );
    }

    private List<Gallery> processGallery(List<FileDTO> fileDTOS) {
        List<Gallery> galleries = new ArrayList<>();
        for (FileDTO fileDTO : fileDTOS) {
            Optional<Gallery> gallery = fileRepository.findById(fileDTO.getId());
            galleries.add(gallery.get());
        }
        return galleries;
    }

    public CourseUpdateDTO getForUpdate(Long id) {
        Optional<Course> byId = repository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("Course not found");
        return mapper.toUpdateDTO(byId.get());
    }


    public Course updateForEveryService(CourseUpdateDTO DTO) {
        Optional<Course> courseOptional = repository.findById(DTO.getId());
        Course course = courseOptional.get();
        Course course1 = mapper.fromUpdateDTO(DTO, course);
        if (Objects.isNull(course)) {
            throw new NotFoundException("Course not found");
        }
        return repository.save(course1);
    }

    public Course createForEveryService(CourseCreateDTO DTO) {
        Course course = fromCreatDto(DTO);
        Course save = repository.save(course);
        return save;

    }
}
