package uz.darkor.darkor_22.repository.auth.userEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.darkor.darkor_22.entity.auth.UserEmployeeDetail;
import uz.darkor.darkor_22.entity.course.Course;
import uz.darkor.darkor_22.repository.BaseRepository;

public interface UserEmployeeDetailRepository extends JpaRepository<UserEmployeeDetail, Long>, BaseRepository {

    @Query(value = "select * from user_employee_detail  e" +
            "    inner join user_employee_detail_courses ec" +
            "        on  ec.courses_id = :course", nativeQuery = true)
    UserEmployeeDetail findByCourses(@Param("course") Long courseId);
}
