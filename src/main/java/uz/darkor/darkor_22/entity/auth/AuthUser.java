package uz.darkor.darkor_22.entity.auth;

import lombok.Getter;
import lombok.Setter;
import uz.darkor.darkor_22.enums.AuthRole;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean block;
    @Column(columnDefinition = "boolean default false")
    private boolean active;
    @Enumerated(EnumType.STRING)
    private AuthRole role;


}
