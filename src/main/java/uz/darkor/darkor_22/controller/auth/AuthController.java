package uz.darkor.darkor_22.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import uz.darkor.darkor_22.controller.AbstractController;
import uz.darkor.darkor_22.dto.auth.AuthShowDto;
import uz.darkor.darkor_22.dto.auth.AuthUserDto;
import uz.darkor.darkor_22.dto.auth.SessionDto;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.auth.AuthUserService;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class AuthController extends AbstractController<AuthUserService> {

    public AuthController(AuthUserService service) {
        super(service);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/api/v1/auth/login")
    public ResponseEntity<Data<SessionDto>>login(@RequestBody AuthUserDto dto, WebRequest request){
        return service.login(dto,request);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/auth/create")

    public ResponseEntity<Data<Long>>create(@RequestBody AuthUserDto dto){
        return service.create(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/auth/delete/{id}")
    public ResponseEntity<Void>  update (@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/auth/getAll", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthShowDto>>> getAll(){
        return service.getAll();
    }

}
