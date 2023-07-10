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
public class UserEmployee extends Auditable {

    @Column(nullable = false)
    private String fullName;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Gallery gallery;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Course> courses;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "userEmployee")
    private UserEmployeeDetail userEmployeeDetail;

    @Column(columnDefinition = "boolean default false")
    private Boolean access;

}
