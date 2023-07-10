package uz.darkor.darkor_22.entity.auth;

import lombok.*;
import org.hibernate.annotations.Where;
import uz.darkor.darkor_22.entity.Auditable;
import uz.darkor.darkor_22.enums.RegisterDataType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "register")
@Where(clause = "is_deleted = false")
public class Register extends Auditable {
    @Column(name = "full_name", nullable = false, updatable = false)
    private String fullName;
    @Column(name = "birthday", nullable = false, updatable = false)
    private String birthday;
    @Column(name = "course_name", nullable = false, updatable = false)
    private String courseName;
    @Column(name = "address", nullable = false, updatable = false)
    private String address;
    @Column(name = "data_type", nullable = false, updatable = false)
    private RegisterDataType dataType;
    @Column(name = "phone_number", nullable = false, updatable = false)
    private String phoneNumber;
}
