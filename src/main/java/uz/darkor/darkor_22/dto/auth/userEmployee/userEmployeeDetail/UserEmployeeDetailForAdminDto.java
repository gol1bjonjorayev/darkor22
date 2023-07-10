package uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmployeeDetailForAdminDto extends GenericDTO {

    private Long id;

    private String title;

    private String description;

    private String userDescription;

    private String youTubeVideo;

    private String phoneNumber;

    private List<FileDTO> gallery;

    private UserEmployeeGetDto userEmployee;

}
