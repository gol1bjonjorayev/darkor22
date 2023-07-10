package uz.darkor.darkor_22.entity.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.darkor.darkor_22.dto.home.statistics.StatisticsGetDTO;
import uz.darkor.darkor_22.entity.Auditable;
import uz.darkor.darkor_22.enums.StatisticsType;

import javax.persistence.*;

@Getter
@Setter
@Table(indexes = @Index(name = "statistics_index", columnList = "code", unique = true))
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted=false")
public class Statistics extends Auditable {

    @Column(nullable = false)
    private String number;

    @Column(nullable = false,columnDefinition = "text")
    private String titleUz;

    @Column(nullable = false,columnDefinition = "text")
    private String titleRu;

    @Column(nullable = false,columnDefinition = "text")
    private String titleEn;

    @Column(nullable = false,columnDefinition = "text")
    private String descriptionUz;

    @Column(nullable = false,columnDefinition = "text")
    private String descriptionRu;

    @Column(nullable = false,columnDefinition = "text")
    private String descriptionEn;

    @Enumerated(value = EnumType.STRING)
    private StatisticsType statisticsType;


}
