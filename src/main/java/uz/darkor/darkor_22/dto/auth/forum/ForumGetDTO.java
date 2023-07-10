package uz.darkor.darkor_22.dto.auth.forum;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ForumGetDTO extends GenericDTO {

    private String fullName;

    private String email;

    private String phone;

    private String message;

    private LocalDateTime createdAt;
}
