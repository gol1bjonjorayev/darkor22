package uz.darkor.darkor_22.dto.auth.forum;

import lombok.*;
import uz.darkor.darkor_22.dto.BaseDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ForumCreateDTO implements BaseDTO {

    private String fullName;

    private String email;

    private String phone;

    private String message;
}
