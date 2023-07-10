package uz.darkor.darkor_22.dto.auth.employee_detail;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeUpdateDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailUpdateDTO extends GenericDTO {

    @Positive
    private Long id;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionUz;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionRu;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionEn;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionUz;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionRu;

    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionEn;

    private String youTubeVideo;

    private List<FileDTO> galleries;

    private EmployeeUpdateDTO employee;


}