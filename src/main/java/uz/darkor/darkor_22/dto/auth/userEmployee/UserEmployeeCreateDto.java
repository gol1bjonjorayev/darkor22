package uz.darkor.darkor_22.dto.auth.userEmployee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.darkor.darkor_22.dto.BaseDTO;
import uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail.UserEmployeeDetailCreateDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmployeeCreateDto implements BaseDTO {

    private String fullName;

    private FileDTO gallery;

    private List<Long> courseIds;

}
