package uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.darkor.darkor_22.dto.BaseDTO;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeCreateDto;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmployeeDetailCreateDto implements BaseDTO {

    private String title;

    private String description;

    @NotNull(message = "user description not be null")
    private String userDescription;

    private String youTubeVideo;

    @NotNull(message = "phone number not be null")
    private String phoneNumber;

    @NotNull(message = "gallery not be null")
    private List<FileDTO> gallery;

    private UserEmployeeCreateDto userEmployee;

}
