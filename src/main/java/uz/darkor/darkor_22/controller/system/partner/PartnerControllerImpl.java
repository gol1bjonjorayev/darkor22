package uz.darkor.darkor_22.controller.system.partner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.system.partner.PartnerCriteria;
import uz.darkor.darkor_22.dto.system.partner.PartnerCreateDTO;
import uz.darkor.darkor_22.dto.system.partner.PartnerGetDTO;
import uz.darkor.darkor_22.dto.system.partner.PartnerUpdateDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.system.partner.PartnerService;
import uz.darkor.darkor_22.service.system.partner.PartnerServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(BaseUtils.PATH +"/partner/*")
public class PartnerControllerImpl extends AbstractController<PartnerServiceImpl>{

    public PartnerControllerImpl(PartnerServiceImpl service) {
        super(service);
    }

    @PostMapping("add")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<PartnerGetDTO>> create(@RequestBody PartnerCreateDTO DTO) {
        return new ResponseEntity<>(new Data<>(service.create(DTO)), HttpStatus.OK);
    }

    @PutMapping("updated")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<PartnerGetDTO>> update(@RequestBody PartnerUpdateDTO DTO) {
        return new ResponseEntity<>(new Data<>(service.update(DTO)), HttpStatus.OK);
    }

    @DeleteMapping("deleted/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<Boolean>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(new Data<>(service.delete(id)), HttpStatus.OK);
    }


   @GetMapping("getOne/{id}")
    public ResponseEntity<Data<PartnerGetDTO>> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Data<List<PartnerGetDTO>>> list() {
        return new ResponseEntity<>(new Data<>(service.getAll()), HttpStatus.OK);
    }
}
