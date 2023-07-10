package uz.darkor.darkor_22.entity.auth;

import lombok.*;
import uz.darkor.darkor_22.entity.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "forum")
public class Forum extends Auditable {
    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(name = "email", nullable = false, length = 120,columnDefinition = "text")
    @Email
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "message", nullable = false, length = 500)
    private String message;
}
