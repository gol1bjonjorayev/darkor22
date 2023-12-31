package uz.darkor.darkor_22.dto.course.course_detail;

import lombok.*;
import uz.darkor.darkor_22.dto.GenericDTO;
import uz.darkor.darkor_22.dto.auth.employee.EmployeeGetDTO;
import uz.darkor.darkor_22.dto.course.course.CourseGetDTO;
import uz.darkor.darkor_22.dto.course.price.PriceGetDTO;
import uz.darkor.darkor_22.dto.system.gallery.FileDTO;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailGetDTO extends GenericDTO {

    private Long id;

    private String titleDescriptionUz;
    private String titleDescriptionRu;
    private String titleDescriptionEn;
    private String bodyDescriptionUz;
    private String bodyDescriptionRu;
    private String bodyDescriptionEn;

    private String secondTitleDescriptionUz;
    private String secondTitleDescriptionRu;
    private String secondTitleDescriptionEn;
    private String secondBodyDescriptionUz;
    private String secondBodyDescriptionRu;
    private String secondBodyDescriptionEn;

    private String youtubeVideo;

    private List<FileDTO> fileUz;

    private List<FileDTO> fileRu;

    private List<FileDTO> fileEn;

    private PriceGetDTO price;

    private CourseGetDTO course;

    public CourseDetailLocalizationDTO getLocalizationDto(String lang) {
        if (lang.equals("uz")) {
            return CourseDetailLocalizationDTO.builder().id(this.id).code(getCode()).titleDescription(this.titleDescriptionUz).secondTitleDescription(this.secondTitleDescriptionUz).bodyDescription(this.bodyDescriptionUz).secondBodyDescription(this.secondBodyDescriptionUz).youtubeVideo(this.youtubeVideo).file(this.fileUz).course(this.course.getLocalizationDto("uz")).price(this.price.getLocalizationDto("uz")).build();
        } else if (lang.equals("ru")) {
            return CourseDetailLocalizationDTO.builder().id(this.id).code(getCode()).titleDescription(this.titleDescriptionRu).secondTitleDescription(this.secondTitleDescriptionRu).bodyDescription(this.bodyDescriptionRu).secondBodyDescription(this.secondBodyDescriptionRu).youtubeVideo(this.youtubeVideo).file(this.fileRu).course(this.course.getLocalizationDto("ru")).price(this.price.getLocalizationDto("ru")).build();
        }
        return CourseDetailLocalizationDTO.builder().id(this.id).code(getCode()).titleDescription(this.titleDescriptionEn).secondTitleDescription(this.secondTitleDescriptionEn).bodyDescription(this.bodyDescriptionEn).secondBodyDescription(this.secondBodyDescriptionEn).youtubeVideo(this.youtubeVideo).file(this.fileEn).course(this.course.getLocalizationDto("en")).price(this.price.getLocalizationDto("en")).build();
    }
}
