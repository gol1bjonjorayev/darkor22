package uz.darkor.darkor_22.service.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import uz.darkor.darkor_22.dto.auth.AuthShowDto;
import uz.darkor.darkor_22.dto.auth.AuthUserDto;
import uz.darkor.darkor_22.dto.auth.SessionDto;
import uz.darkor.darkor_22.entity.auth.AuthUser;
import uz.darkor.darkor_22.enums.AuthRole;
import uz.darkor.darkor_22.exception.NotFoundException;
import uz.darkor.darkor_22.exception.UserAlreadyTaken;
import uz.darkor.darkor_22.repository.user.AuthUserRepository;
import uz.darkor.darkor_22.response.APIErrorDTO;
import uz.darkor.darkor_22.response.Data;
import uz.darkor.darkor_22.service.BaseService;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthUserService implements UserDetailsService, BaseService {

    private final AuthUserRepository repository;
    private final PasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthUserService(AuthUserRepository repository, PasswordEncoder encoder, ObjectMapper objectMapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = repository.find(true, username);

        AuthUser authUser = user;
        return User.builder().username(authUser.getUsername()).password(user.getPassword()).authorities(Arrays.asList(new SimpleGrantedAuthority(authUser.getRole().name()))).build();

    }

    public ResponseEntity<Data<SessionDto>> login(AuthUserDto dto, WebRequest request) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
//            System.out.println(properties.getRequest() + properties.getApi());
            HttpPost httppost = new
                    HttpPost("http://172.105.11.236:80/" + "api/login");

            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
            JsonNode apiError = json_auth.get("apiError");

            if (apiError == null) {
                JsonNode node = json_auth.get("body");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new Data<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Data<>(APIErrorDTO.builder().message("bad request").path(request.getContextPath()).status(HttpStatus.BAD_REQUEST.value()).build()), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new Data<>(APIErrorDTO.builder().message("bad request").path(request.getContextPath()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build()), HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<Data<Long>> create(AuthUserDto dto) {
        AuthUser user = new AuthUser();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setActive(true);
        user.setBlock(false);
        user.setRole(AuthRole.ADMIN);
        try {
            Long id = repository.save(user).getId();
            return new ResponseEntity<>(new Data<>(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new UserAlreadyTaken("username already taken");
        }

    }

    public void delete(Long id) {
        Optional<AuthUser> byId = repository.findById(id);
        if (byId.isEmpty()) throw  new NotFoundException("USER_NOT_FOUND");
        repository.delete(byId.get());
    }


    public ResponseEntity<Data<List<AuthShowDto>>> getAll() {
        List<AuthUser> users = repository.findAll();
        List<AuthShowDto> dto = new ArrayList<>();
        for (AuthUser user : users) {
            AuthShowDto authShowDto = new AuthShowDto();
            authShowDto.setId(user.getId());
            authShowDto.setUsername(user.getUsername());
            dto.add(authShowDto);
        }

        return new ResponseEntity(new Data<>(dto), HttpStatus.OK);
    }
}
