package uz.darkor.darkor_22.dto.auth.register;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.enums.RegisterDataType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGetDTO extends GenericDTO {
    private Long id;
    private String fullName;
    private String birthday;
    private String courseName;
    private String address;
    private RegisterDataType dataType;
    private String phoneNumber;
    private LocalDateTime createdAt;
}
