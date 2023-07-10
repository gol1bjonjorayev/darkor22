package uz.darkor.darkor_22.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.criteria.auth.userEmployee.UserEmployeeCriteria;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeShowDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailForAdminDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailGetDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.auth.userEmployee.UserEmployeeDetailService;
import uz.darkor.darkor_22.service.auth.userEmployee.UserEmployeeService;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(BaseUtils.PATH + "/user-employee")
public class UserEmployeeController {

    private final UserEmployeeService ues;
    private final UserEmployeeDetailService ueds;

    public UserEmployeeController(UserEmployeeService ues, UserEmployeeDetailService ueds) {
        this.ues = ues;
        this.ueds = ueds;
    }

    @RequestMapping(value = "/get-by-course/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<UserEmployeeGetDto>>> getAllByCourse(@PathVariable Long id, @Valid UserEmployeeCriteria criteria, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(ues.getAllByCourse(criteria, id, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "/give-access/{id}/{access}", method = RequestMethod.POST)
    public ResponseEntity<Data<Boolean>> giveAccess(@PathVariable Long id, @PathVariable Boolean access){
       return new ResponseEntity<>(new Data<>( ues.givePermission(id, access)), HttpStatus.OK);
    }

    @PostMapping("/create-with-detail/")
    public ResponseEntity<Data<UserEmployeeDetailGetDto>> create(@RequestBody @Valid UserEmployeeDetailCreateDto dto, @RequestHeader("accept-language") String lang){
     return new ResponseEntity<>(new Data<>(ueds.create(dto, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-detail/{userEmployeeId}", method = RequestMethod.GET)
    public ResponseEntity<Data<UserEmployeeDetailGetDto>> getDetail(@PathVariable Long userEmployeeId,  @RequestHeader("accept-language") String lang){
        return new ResponseEntity<>(new Data<>(ueds.getDetail(userEmployeeId, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-detail-for-admin/{userEmployeeId}", method = RequestMethod.GET)
    public ResponseEntity<Data<UserEmployeeDetailForAdminDto>> getDetailForAdmin(@PathVariable Long userEmployeeId, @RequestHeader("accept-language") String lang){
        return new ResponseEntity<>(new Data<>(ueds.getDetailForAdmin(userEmployeeId, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-for-admin", method = RequestMethod.GET)
    public ResponseEntity<Data<List<UserEmployeeGetDto>>> getAllForAdmin(@Valid UserEmployeeCriteria criteria, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(ues.getAllForAdmin(criteria, lang)), HttpStatus.OK);
    }

}
