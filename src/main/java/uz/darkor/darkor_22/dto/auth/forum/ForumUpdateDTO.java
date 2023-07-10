package uz.darkor.darkor_22.dto.auth.forum;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ForumUpdateDTO extends GenericDTO {
    private String fullName;

    private String email;

    private String phone;

    private String message;
}
