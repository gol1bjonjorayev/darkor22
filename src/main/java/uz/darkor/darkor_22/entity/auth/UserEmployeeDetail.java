package uz.darkor.darkor_22.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import uz.darkor.darkor_22.entity.Auditable;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.entity.system.Gallery;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEmployeeDetail extends Auditable {

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String userDescription;

    private String youTubeVideo;

    private String phoneNumber;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Gallery> gallery;

    @Fetch(FetchMode.SELECT)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private UserEmployee userEmployee;

}
