package uz.darkor.darkor_22.service.course.skill;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.skill.SkillCriteria;
import uz.darkor.darkor_22.dto.course.skill.SkillCreateDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillLocalizedDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillUpdateDTO;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.entity.course.Skill;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.mapper.skill.SkillMapper;
import uz.darkor.darkor_22.repository.course.CourseRepository;
import uz.darkor.darkor_22.repository.skill.SkillRepository;
import uz.darkor.darkor_22.service.AbstractService;

import java.util.*;

@Service
public class SkillServiceImpl extends AbstractService<SkillMapper, SkillRepository>
        implements SkillService {

    private final CourseRepository courseRepository;

    public SkillServiceImpl(SkillMapper mapper,
                            SkillRepository repository,
                            CourseRepository courseRepository) {
        super(mapper, repository);
        this.courseRepository = courseRepository;
    }

    @Override
    public SkillLocalizedDTO create(SkillCreateDTO DTO) {
        Skill skill = mapper.fromCreateDTO(DTO);
        Optional<Course> byId = courseRepository.findById(DTO.getCourse().getId());
        if (byId.isEmpty()) throw new NotFoundException("COURSE_NOT_FOUND");
        skill.setCourse(byId.get());
        Skill save = repository.save(skill);
        return mapper.toGetDTO(save).getLocalizationDto("uz");
    }

    @Override
    public SkillLocalizedDTO update(SkillUpdateDTO DTO) {
        Skill target = checkExistenceAndGetById(DTO.getCode());
        Skill skill = mapper.fromUpdateDTO(DTO, target);
        Skill updateSkill = repository.save(skill);
        return mapper.toGetDTO(updateSkill).getLocalizationDto("uz");
    }

    @Override
    public Boolean delete(UUID key) {
        try {
            Skill skill = repository.findByCode(key).orElseThrow(() -> {
                throw new NotFoundException("Skill not found !");
            });
            skill.setDeleted(true);
            repository.save(skill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public SkillLocalizedDTO get(UUID key, String language) {
        Skill skill = checkExistenceAndGetById(key);
        return mapper.toGetDTO(skill).getLocalizationDto(language);
    }

    @Override
    public List<SkillLocalizedDTO> list(SkillCriteria criteria, String language) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Skill> skills = repository.findAll(request).getContent();
        return getLocalizedDTOs(skills, language);
    }

    public List<SkillLocalizedDTO> listMy(List<Long> ids, String language) {
        List<Skill> skills = new ArrayList<>();
        for (Long id : ids) {
            Optional<Course> byId = courseRepository.findById(id);
            if (byId.isEmpty()) throw new NotFoundException("Course_Not_Found");
            skills.addAll(repository.findAllByCourse(byId.get()));
        }

        return getLocalizedDTOs(skills, language);
    }

    @Override
    public List<SkillLocalizedDTO> getByCourseCode(Long id, String lang) {
        Optional<Course> courseById = courseRepository.findById(id);
        Course course = courseById.get();
        if (courseById.isEmpty()) throw  new NotFoundException("Skill not found");
        if (Objects.isNull(course))
            throw new NotFoundException("COURSE_NOT_FOUND");
        List<Skill> skills = repository.findByCourse(course);
        return getLocalizedDTOs(skills, lang);
    }

    private List<SkillLocalizedDTO> getLocalizedDTOs(List<Skill> skills, String lang) {
        List<SkillLocalizedDTO> skillLocalizedDTOS = new ArrayList<>();
        for (Skill skill : skills) {
            skillLocalizedDTOS.add(mapper.toGetDTO(skill).getLocalizationDto(lang));
        }

        List<SkillLocalizedDTO> response = new ArrayList<>();
        for (int i = 0; i <= skillLocalizedDTOS.toArray().length-1; i++) {
            for (int j = i+1; j <= skillLocalizedDTOS.toArray().length-1; j++) {
                if (skillLocalizedDTOS.get(i).getName().equals(skillLocalizedDTOS.get(j).getName())&& i!=j) {
//                    response.add(skillLocalizedDTOS.get(i));
                    skillLocalizedDTOS.remove(j);
                }
            }
        }

        return skillLocalizedDTOS;
    }

    private Skill checkExistenceAndGetById(UUID code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("SKILL_NOT_FOUND"));
    }
}
