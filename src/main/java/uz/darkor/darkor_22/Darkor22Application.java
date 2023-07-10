package uz.darkor.darkor_22;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uz.darkor.darkor_22.entity.auth.AuthUser;
import uz.darkor.darkor_22.enums.AuthRole;
import uz.darkor.darkor_22.properties.OpenAPIProperties;
import uz.darkor.darkor_22.properties.ServiceProperties;
import uz.darkor.darkor_22.repository.user.AuthUserRepository;

@OpenAPIDefinition
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({OpenAPIProperties.class, ServiceProperties.class})
public class Darkor22Application {

    public static void main(String[] args) {
        SpringApplication.run(Darkor22Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
