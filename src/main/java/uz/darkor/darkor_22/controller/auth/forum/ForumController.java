package uz.darkor.darkor_22.controller.auth.forum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.auth.forum.ForumCriteria;
import uz.darkor.darkor_22.dto.auth.forum.ForumCreateDTO;
import uz.darkor.darkor_22.dto.auth.forum.ForumGetDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.auth.forum.ForumService;
import uz.darkor.darkor_22.utils.BaseUtils;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = BaseUtils.PATH + "/forum/*")
public class ForumController extends AbstractController<ForumService> {
    public ForumController(ForumService service) {
        super(service);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Data<ForumGetDTO>> create(@RequestBody ForumCreateDTO dto) {
        return new ResponseEntity<>(new Data<>(service.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<ForumGetDTO>>> list(ForumCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria), service.getSize()), HttpStatus.OK);
    }
}
