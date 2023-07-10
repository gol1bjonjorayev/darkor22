package uz.darkor.darkor_22.dto.auth.userEmployee.userEmployeeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.userEmployee.UserEmployeeGetDto;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.entity.system.Gallery;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmployeeDetailGetDto extends GenericDTO {

    private Long id;

    private String title;

    private String description;

    private String userDescription;

    private String youTubeVideo;

    private List<FileDTO> gallery;

    private UserEmployeeGetDto userEmployee;

}
