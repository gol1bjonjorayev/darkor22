package uz.darkor.darkor_22.dto.auth.employee_detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.darkor.darkor_22.dto.BaseDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeLocalizedDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailCreateDTO implements BaseDTO {

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionUz;

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionRu;

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String titleDescriptionEn;

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionUz;

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionRu;

    @NotBlank(message = "title cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String bodyDescriptionEn;

    private String youTubeVideo;

    @NotBlank(message = "gallery cannot be null")
    private List<FileDTO> galleries;

//    @NotNull(message = "employee cannot be null")
    private EmployeeLocalizedDTO employee;


}
