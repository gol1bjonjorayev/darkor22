package uz.darkor.darkor_22.dto.auth.register;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.enums.RegisterDataType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUpdateDTO extends GenericDTO {
    private String fullName;
    private String birthday;
    private String courseName;
    private String address;
    private RegisterDataType dataType;
    private String phoneNumber;
}
