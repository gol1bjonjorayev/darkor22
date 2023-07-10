package uz.darkor.darkor_22.controller.course.skill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.skill.SkillCriteria;
import uz.darkor.darkor_22.dto.course.skill.SkillCreateDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillLocalizedDTO;
import uz.darkor.darkor_22.dto.course.skill.SkillUpdateDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.course.skill.SkillServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;
import uz.darkor.darkor_22.utils.ForList;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(BaseUtils.PATH + "/skill/*")
public class SkillControllerImpl extends AbstractController<SkillServiceImpl>
        implements SkillController {


    public SkillControllerImpl(SkillServiceImpl service) {
        super(service);
    }

    @Override
    public ResponseEntity<Data<SkillLocalizedDTO>> create(SkillCreateDTO DTO, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.create(DTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<SkillLocalizedDTO>> update(SkillUpdateDTO DTO,String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.update(DTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(UUID code,String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.delete(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<SkillLocalizedDTO>> get(UUID code, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.get(code,lang)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<SkillLocalizedDTO>>> list(SkillCriteria criteria, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.list(criteria,lang)), HttpStatus.OK);
    }

    @PostMapping("get_by_courses/")
    public ResponseEntity<Data<List<SkillLocalizedDTO>>> listMy(@RequestBody @Valid ForList courses, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.listMy(courses.getCourseIds(), lang)), HttpStatus.OK);
    }

    @GetMapping("get_by_course/{id}")
    public ResponseEntity<Data<List<SkillLocalizedDTO>>> getByCourse(@PathVariable Long id,
                                                               @RequestHeader("accept-language") String lang){
       BaseUtils.setSessionLang(lang);
       return new ResponseEntity<>(new Data<>(service.getByCourseCode(id, lang)),HttpStatus.OK);
    }
}
