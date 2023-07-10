package uz.darkor.darkor_22.dto.auth.employee_detail;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeGetDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;
import uz.darkor.darkor_22.utils.BaseUtils;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeDetailGetDTO extends GenericDTO {

    private Long id;
    private String titleDescriptionUz;

    private String titleDescriptionRu;

    private String titleDescriptionEn;

    private String bodyDescriptionUz;

    private String bodyDescriptionRu;

    private String bodyDescriptionEn;

    private List<FileDTO> gallery;

    private EmployeeGetDTO employee;

    private String youTubeVideo;


    public EmployeeDetailLocalizedDTO getLocalizationDto() {
        String lang = BaseUtils.getSessionLang();
        return switch (lang) {
            case "en" -> EmployeeDetailLocalizedDTO.builder()
                    .code(this.getCode())
                    .id(this.getId())
                    .titleDescription(this.titleDescriptionEn)
                    .bodyDescription(this.bodyDescriptionEn)
                    .youTubeVideo(this.youTubeVideo)
                    .employee(this.employee.getLocalizationDto())
                    .galleries(this.gallery)
                    .build();
            case "ru" -> EmployeeDetailLocalizedDTO.builder()
                    .code(this.getCode())
                    .id(this.getId())
                    .titleDescription(this.titleDescriptionRu)
                    .bodyDescription(this.bodyDescriptionRu)
                    .youTubeVideo(this.youTubeVideo)
                    .employee(this.employee.getLocalizationDto())
                    .galleries(this.gallery)
                    .build();
            default -> EmployeeDetailLocalizedDTO.builder()
                    .code(this.getCode())
                    .id(this.getId())
                    .titleDescription(this.titleDescriptionUz)
                    .bodyDescription(this.bodyDescriptionUz)
                    .youTubeVideo(this.youTubeVideo)
                    .employee(this.employee.getLocalizationDto())
                    .galleries(this.gallery)
                    .build();

        };
    }

}
