package uz.darkor.darkor_22.controller.course.course.courseDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.course.CourseDetailCriteria;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailCreateDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailGetDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailLocalizationDTO;
import uz.darkor.darkor_22.dto.course.course_detail.CourseDetailUpdateDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.course.course.courseDetails.CourseDetailsServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = BaseUtils.PATH + "/courseDetails/*")
public class CourseDetailControllerImpl extends AbstractController<CourseDetailsServiceImpl> {

    public CourseDetailControllerImpl(CourseDetailsServiceImpl service) {
        super(service);
    }

    @PostMapping("create/")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<CourseDetailLocalizationDTO>> createMy(@RequestBody CourseDetailCreateDTO DTO) {
        CourseDetailLocalizationDTO courseDetailLocalizationDTO = service.create(DTO);
        return new ResponseEntity<>(new Data<>(courseDetailLocalizationDTO), HttpStatus.OK);
    }

    @PutMapping("update/")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<CourseDetailLocalizationDTO>> updateMy(@RequestBody CourseDetailUpdateDTO DTO) {
        CourseDetailLocalizationDTO update = service.update(DTO);
        return new ResponseEntity<>(new Data<>(update), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<Boolean>> deleteMy(@PathVariable Long id) {
        Boolean delete = service.delete(id);
        return new ResponseEntity<>(new Data<>(delete), HttpStatus.OK);
    }

    @RequestMapping(value ="get/", method = RequestMethod.GET)
    public ResponseEntity<Data<CourseDetailLocalizationDTO>> getMy(Long id, @RequestHeader("accept-language") String lang) {
        CourseDetailLocalizationDTO courseDetailLocalizationDTO = service.get(id, lang);
        return new ResponseEntity<>(new Data<>(courseDetailLocalizationDTO), HttpStatus.OK);
    }


    @PutMapping("for/update/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<CourseDetailUpdateDTO>> forUpdate(@PathVariable Long id, @RequestHeader("accept-language") String lang) {
        CourseDetailUpdateDTO update = service.getForUpdate(id, lang);
        return new ResponseEntity<>(new Data<>(update), HttpStatus.OK);
    }


//    @GetMapping("get/list/")
//    public ResponseEntity<Data<List<CourseDetailLocalizationDTO>>> listMy(@RequestBody CourseDetailCriteria criteria, @RequestHeader("accept-language") String lang) {
//        List<CourseDetailLocalizationDTO> list = service.list(criteria, lang);
//        return new ResponseEntity<>(new Data<>(list), HttpStatus.OK);
//    }

}
