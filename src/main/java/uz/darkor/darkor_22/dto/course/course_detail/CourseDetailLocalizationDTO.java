package uz.darkor.darkor_22.dto.course.course_detail;

import lombok.*;
import uz.darkor.darkor_22.dto.BaseDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.course.price.PriceLocalizedDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailLocalizationDTO implements BaseDTO {

    private Long id;
    private UUID code;
    private String titleDescription;
    private String bodyDescription;
    private String secondTitleDescription;
    private String secondBodyDescription;
    private String youtubeVideo;
    private List<FileDTO> file;
    private PriceLocalizedDTO price;
    private CourseLocalizationDTO course;
}
