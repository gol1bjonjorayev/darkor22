package uz.darkor.darkor_22.controller.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.criteria.post.PostCriteria;
import uz.darkor.darkor_22.dto.home.post.PostCreateDTO;
import uz.darkor.darkor_22.dto.home.post.PostGetDTO;
import uz.darkor.darkor_22.dto.home.post.PostLocalizedDTO;
import uz.darkor.darkor_22.dto.home.post.PostUpdateDTO;
import uz.darkor.darkor_22.enums.PostType;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.post.PostServiceImpl;
import uz.darkor.darkor_22.utils.BaseUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = BaseUtils.PATH+"/post")
public class PostControllerImpl extends AbstractController<PostServiceImpl>{
    public PostControllerImpl(PostServiceImpl service) {
        super(service);
    }


    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Data<PostLocalizedDTO>> create(@RequestBody PostCreateDTO DTO, @RequestHeader("accept-language") String lang ) {
        return new ResponseEntity<>(new Data<>(service.create(DTO,lang)), HttpStatus.CREATED);
    }

   @PutMapping("/updated")
//   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Data<PostLocalizedDTO>> update(@RequestBody PostUpdateDTO DTO, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.update(DTO,lang)),HttpStatus.OK);
    }

    @DeleteMapping("/deleted/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Data<Boolean>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(new Data<>(service.delete(id)),HttpStatus.OK);
    }

  @GetMapping("/getOne/{id}/{postType}")
    public ResponseEntity<Data<PostLocalizedDTO>> getService(@PathVariable Long id, @PathVariable PostType postType, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.getService(id,postType, lang)),HttpStatus.OK);
    }

   @GetMapping("/list/{postType}")
    public ResponseEntity<Data<List<PostLocalizedDTO>>> listService(@Valid  PostCriteria criteria,@PathVariable PostType postType, @RequestHeader("accept-language") String lang) {
        return new ResponseEntity<>(new Data<>(service.listService(criteria,postType, lang)),HttpStatus.OK);
    }

}
