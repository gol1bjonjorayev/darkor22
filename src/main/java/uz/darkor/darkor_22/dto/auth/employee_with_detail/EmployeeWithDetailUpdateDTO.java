package uz.darkor.darkor_22.dto.auth.employee_with_detail;


import lombok.Getter;
import lombok.Setter;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeUpdateDTO;
import uz.darkor.darkor_22.dto.auth.employee_detail.EmployeeDetailUpdateDTO;
import uz.darkor.darkor_22.dto.course.course.CourseLocalizationDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class EmployeeWithDetailUpdateDTO {


    @NotBlank(message = "full name in uz cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String fullNameUz;

    @NotBlank(message = "full name in ru cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String fullNameRu;

    @NotBlank(message = "full name in en cannot be null or empty")
    @Size(min = 3, max = 255, message = "The length of the name field must be between 3 and 255")
    private String fullNameEn;

    @NotBlank(message = "type cannot be null or empty")
    private String type;

    @NotNull(message = "course cannot be null")
    private List<CourseLocalizationDTO> courses;

    private String youTubeVideo;

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

    @NotBlank(message = "gallery cannot be null")
    private List<FileDTO> galleries;


}
