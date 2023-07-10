package uz.darkor.darkor_22;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.darkor.darkor_22.dto.auth.AuthUserDto;
import uz.darkor.darkor_22.service.auth.AuthUserService;

import java.util.Scanner;

@Component
public class Linar implements CommandLineRunner {
    private final AuthUserService service;

    public Linar(AuthUserService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUsername("darkoradmin");
        authUserDto.setPassword("darkor2022!");
        Scanner scanner= new Scanner(System.in);
        scanner.nextInt();
       try {
           service.create(authUserDto);
       }catch (RuntimeException e){
           System.out.println(e.getMessage());
       }
    }
}
