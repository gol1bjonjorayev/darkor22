package uz.darkor.darkor_22.dto.auth.userEmployee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailGetDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEmployeeShowDto extends GenericDTO {

    private Long id;

    private String fullName;

    private FileDTO gallery;

    private List<CourseLocalizationDTO> courses;

    private Boolean access;


}
