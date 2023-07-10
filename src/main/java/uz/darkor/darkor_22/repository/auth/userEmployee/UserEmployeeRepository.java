package uz.darkor.darkor_22.repository.auth.userEmployee;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.darkor.darkor_22.entity.auth.UserEmployee;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;
public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Long>, BaseRepository {

    List<UserEmployee> findAllByCoursesAndAccessTrue(Course course, Sort sort);

    List<UserEmployee> findAllByOrderByIdDesc();

}
