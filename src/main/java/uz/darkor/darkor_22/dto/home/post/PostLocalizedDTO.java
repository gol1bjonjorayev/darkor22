package uz.darkor.darkor_22.dto.home.post;

import lombok.*;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.enums.PostType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostLocalizedDTO {

    private Long id;

    private FileDTO gallery;

    private  String title;

    private  String description;

    private PostType postType;

    private String youTubeVideo;
}
