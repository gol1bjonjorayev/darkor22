package uz.darkor.darkor_22.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;import uz.darkor.darkor_22.entity.Auditable;
import uz.darkor.darkor_22.entity.system.Gallery;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(indexes = @Index(name = "employee_detail_index", columnList = "code", unique = true))
public class EmployeeDetail extends Auditable {
    @Column(nullable = false)
    private String titleDescriptionUz;

    @Column(nullable = false)
    private String titleDescriptionRu;

    @Column(nullable = false)
    private String titleDescriptionEn;

    @Column(nullable = false)
    private String bodyDescriptionUz;

    @Column(nullable = false)
    private String bodyDescriptionRu;

    @Column(nullable = false)
    private String bodyDescriptionEn;

    private String youTubeVideo;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Gallery> gallery;

    @Fetch(FetchMode.SELECT)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Employee employee;

}
