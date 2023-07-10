package uz.darkor.darkor_22.dto.auth.register;

import lombok.*;
import uz.darkor.darkor_22.dto.BaseDTO;
import uz.darkor.darkor_22.enums.RegisterDataType;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCreateDTO implements BaseDTO {
    @NotBlank(message = "full name cannot be null")
    private String fullName;
    @NotBlank(message = "birth day cannot be null")
    private String birthday;
    @NotBlank(message = "course name cannot be null")
    private String courseName;
    @NotBlank(message = "address cannot be null")
    private String address;
    @NotBlank(message = "data type cannot be null")
    private RegisterDataType dataType;
    @NotBlank(message = "phone number cannot be null")
    private String phoneNumber;
}
