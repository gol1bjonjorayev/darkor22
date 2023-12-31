package uz.darkor.darkor_22.controller.faq;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.faq.FAQCriteria;
import uz.darkor.darkor_22.dto.system.faq.FAQCreateDTO;
import uz.darkor.darkor_22.dto.system.faq.FAQGetDTO;
import uz.darkor.darkor_22.dto.system.faq.FAQLocalizedDTO;
import uz.darkor.darkor_22.dto.system.faq.FAQUpdateDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.faq.FAQServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = BaseUtils.PATH + "/faq/*")
public class FAQControllerImpl extends AbstractController<FAQServiceImpl> implements FAQController {
    public FAQControllerImpl(FAQServiceImpl service) {
        super(service);
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<FAQLocalizedDTO>> create(FAQCreateDTO DTO, String lang) {
        return new ResponseEntity<>(new Data<>(service.create(DTO)), HttpStatus.CREATED);
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<FAQLocalizedDTO>> update(FAQUpdateDTO DTO, String lang) {
        return new ResponseEntity<>(new Data<>(service.update(DTO)), HttpStatus.OK);
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Data<Boolean>> delete(UUID code, String lang) {
        return new ResponseEntity<>(new Data<>(service.delete(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<FAQLocalizedDTO>> get(UUID code, String lang) {
        return new ResponseEntity<>(new Data<>(service.get(code, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "get_with_get_dto/{code}", method = RequestMethod.GET)
    public ResponseEntity<Data<FAQGetDTO>> getWithGetDTO(@PathVariable UUID code) {
        return new ResponseEntity<>(new Data<>(service.getWithGetDTO(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<FAQLocalizedDTO>>> list(FAQCriteria criteria, String lang) {
        return new ResponseEntity<>(new Data<>(service.list(criteria, lang)), HttpStatus.OK);
    }

    @RequestMapping(value = "list_by_course_code/{courseCode}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<FAQLocalizedDTO>>> listByCourseCode(FAQCriteria criteria, String lang, @PathVariable UUID courseCode) {
        return new ResponseEntity<>(new Data<>(service.listByCourseCode(criteria, lang, courseCode)), HttpStatus.OK);
    }
}
