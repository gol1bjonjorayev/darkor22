package uz.darkor.darkor_22.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.employee.EmployeeCriteria;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeCreateDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeLocalizedDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeUpdateDTO;
import uz.darkor.darkor_22.dto.auth.employee_with_detail.EmployeeWithDetailCreatDTO;
import uz.darkor.darkor_22.exception.validator.BadRequestException;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.employee.employee.EmployeeService;
import uz.darkor.darkor_22.service.employee.employee.EmployeeServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = BaseUtils.PATH + "/employee/*")
public class EmployeeControllerImpl extends AbstractController<EmployeeServiceImpl> implements EmployeeController {

    public EmployeeControllerImpl(EmployeeServiceImpl service) {
        super(service);
    }

    @PostMapping(value = "create_with_detail")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<EmployeeLocalizedDTO>> createWithDetail(@Valid  @RequestBody EmployeeWithDetailCreatDTO dto, @RequestHeader("accept-language") String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.createWithDetail(dto)), HttpStatus.OK);
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<EmployeeLocalizedDTO>> create(EmployeeCreateDTO DTO, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.create(DTO)), HttpStatus.OK);
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<EmployeeLocalizedDTO>> update(EmployeeUpdateDTO DTO, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.update(DTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<EmployeeLocalizedDTO>> get(UUID code, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.get(code, lang)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<EmployeeLocalizedDTO>>> list(EmployeeCriteria criteria, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.list(criteria, lang)), HttpStatus.OK);
    }


    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<Boolean>> delete(UUID code, String lang) {
        throw new BadRequestException("QANCHA_TUSHUNTIRISH_KERAK_DELETE_QILISH_UCHUN_BU_/api/v1/employee/delete/_APIDAN_FOIDALANISH_KERAK");
    }

    @DeleteMapping("delete/")
    public ResponseEntity<Data<Boolean>> delete(Long id, @RequestHeader("accept-language") String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.deleteMy(id)), HttpStatus.OK);
    }

    @GetMapping("get_by_course/{code}")
    public ResponseEntity<Data<List<EmployeeLocalizedDTO>>> getByCourseCode(EmployeeCriteria criteria, @PathVariable UUID code, String lang) {
        BaseUtils.setSessionLang(lang);
        return new ResponseEntity<>(new Data<>(service.getAllByCourseCode(criteria, code)), HttpStatus.OK);
    }

}
