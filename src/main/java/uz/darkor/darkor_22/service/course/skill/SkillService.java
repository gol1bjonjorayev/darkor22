package uz.darkor.darkor_22.service.course.skill;


import uz.darkor.darkor_22.criteria.skill.SkillCriteria;
import uz.darkor.darkor_22.dto.course.skill.SkillCreateDTO;

import uz.darkor.darkor_22.dto.course.skill.SkillLocalizedDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillUpdateDTO;
import uz.darkor.darkor_22.service.BaseService;
import uz.darkor.darkor_22.service.GenericCUDService;
import uz.darkor.darkor_22.service.GenericGLService;

import java.util.List;
import java.util.UUID;

public interface SkillService extends GenericCUDService<SkillCreateDTO, SkillUpdateDTO, SkillLocalizedDTO, UUID>,
        GenericGLService<SkillLocalizedDTO, SkillCriteria, UUID>, BaseService {
    List<SkillLocalizedDTO> getByCourseCode(Long id, String lang);
}
