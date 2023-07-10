package uz.darkor.darkor_22.controller.course.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.course.CourseCriteria;
import uz.darkor.darkor_22.dto.course.course.CourseCreateDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.course.course.CourseUpdateDTO;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.course.course.CourseServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = BaseUtils.PATH + "/course/*")
public class    CourseControllerImpl extends AbstractController<CourseServiceImpl> {
    public CourseControllerImpl(CourseServiceImpl service) {
        super(service);
    }


//    @PostMapping("create/")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    public ResponseEntity<Data<CourseLocalizationDTO>> createMy(@RequestBody CourseCreateDTO DTO) {
//        return new ResponseEntity<>(new Data<>(service.create(DTO)), HttpStatus.CREATED);
//    }

//    @PutMapping("update/")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    public ResponseEntity<Data<CourseLocalizationDTO>> updateMy(@RequestBody CourseUpdateDTO DTO) {
//        return new ResponseEntity<>(new Data<>(service.update(DTO)), HttpStatus.OK);
//    }

//    @DeleteMapping("delete/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    public ResponseEntity<Data<Boolean>> delete(@PathVariable Long id) {
//        return new ResponseEntity<>(new Data<>(service.delete(id)), HttpStatus.OK);
//    }
    

    @GetMapping("list/")
    public ResponseEntity<Data<List<CourseLocalizationDTO>>> listMy(@Valid CourseCriteria criteria, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.list(criteria, lang)), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Data<CourseLocalizationDTO>> getOne(@PathVariable Long id, @RequestHeader("accept-language") String lang){
        return new ResponseEntity<>(new Data(service.getOne(id, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "list-for-filter/", method = RequestMethod.GET)
    public ResponseEntity<Data<List<CourseLocalizationDTO>>> listForFilter(@RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.listForFilter(lang)), HttpStatus.OK);
    }

    @GetMapping("forUpdate/{id}")
    public ResponseEntity<Data<CourseUpdateDTO>> getForUpdateCourse(@PathVariable Long id) {
        return new ResponseEntity<>(new Data<>(service.getForUpdate(id)), HttpStatus.OK);
    }
}
