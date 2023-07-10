package uz.darkor.darkor_22.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.darkor.darkor_22.service.auth.AuthUserService;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"};
    private static final String[] ALLOWED_HEADERS = {
            "accept", "cache-Control", "authorization", "content-type", "x-auth-token", "cookie", "set-cookie",
            "content-disposition", "dnt", "if-modified-since", "keep-alive", "origin", "user-agent", "x-requested-with"
    };


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList(ALLOWED_METHODS));
        configuration.setAllowedHeaders(Arrays.asList(ALLOWED_HEADERS));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(MAX_AGE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    public final static String[] WHITE_LIST = {

            "/api/login"
            , "/api/v1/auth/login"
            , "/swagger-ui/**"
            ,"/api/docs/**"
            ,"/api/v1/carousel/getOne/**"
            ,"/api/v1/carousel/list"
            ,"/api/v1/homeService/getOne/**"
            ,"/api/v1/homeService/list/**"
            ,"/api/v1/post/getOne/**"
            ,"/api/v1/post/list/**"
            ,"/api/v1/statistics/getOne/**"
            ,"/api/v1/statistics/list/**"
            ,"/api/v1/course/list/"
            ,"/api/v1/course/list-for-filter/"
            ,"/api/v1/course/forUpdate/**"
            ,"/api/v1/course/get/**"
            ,"/api/v1/courseDetails/get/"
            ,"/api/v1/download/**"
            ,"/upload/**"
            ,"/api/v1/comment/getOneComment/**"
            ,"/api/v1/comment/listComment"
            ,"/api/v1/comment/getAllByCourse/**"
            ,"/api/v1/partner/getOne/**"
            ,"/api/v1/partner/list"
            ,"/api/v1/faq/list"
            ,"/api/v1/faq/get/**"
            ,"/api/v1/faq/get_with_get_dto/**"
            ,"/api/v1/faq/list_by_course_code/**"
            ,"/api/v1/employee/get/**"
            ,"/api/v1/employee/list/**"
            ,"/api/v1/employee/get_by_course/**"
            ,"/api/v1/employee_detail/get_by_course/**"
            ,"/api/v1/employee_detail/get_by_employee/**"
            ,"/api/v1/graduated/get/**"
            ,"/api/v1/graduated/list/**"
            ,"/api/v1/graduated/get_by_course/**"
            ,"/api/v1/price/get/**"
            ,"/api/v1/price/list/**"
            ,"/api/v1/skill/get/**"
            ,"/api/v1/skill/list/**"
            ,"/api/v1/skill/get_by_course/**"
            ,"/api/v1/skill/get_by_courses/"
            ,"/api/v1/forum/create"
            ,"/api/v1/user-employee/create-with-detail/"
            ,"/api/v1/user-employee/get-by-course/**"
            ,"/api/v1/user-employee/get-detail/**"
    };

    private final AuthUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.cors(AbstractHttpConfigurer::disable);
        http.cors();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
