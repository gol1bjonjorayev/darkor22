package uz.darkor.darkor_22.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.darkor.darkor_22.entity.auth.Employee;
import uz.darkor.darkor_22.enums.EmployeeType;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, BaseRepository {
    Optional<Employee> findByCode(UUID code);

    @Modifying
    @Query(value = "delete from Employee where code = :key")
    void deleteByCode(@Param("key") UUID key);

    @Query(value = "select * from employee  e" +
            "    inner join employee_courses ec" +
            "        on  ec.courses_id = :course" +
            "            and e.type = 'EXPERT'", nativeQuery = true)
    List<Employee> findAllByCourses(@Param("course") Long courseId);

    @Query(value = "select * from employee  e" +
            "    inner join employee_courses ec" +
            "        on  ec.courses_id = :course" +
            "            and e.type = :type", nativeQuery = true)
    List<Employee> findAllByCoursesAndType(@Param("course") Long courseId, @Param("type") String type);

    @Query(value = "select * from employee  e" +
            "    inner join employee_courses ec" +
            "        on  ec.courses_id = :course", nativeQuery = true)
    List<Employee> findAllByCoursesAny(@Param("course") Long courseId);

    List<Employee> findAllByType(EmployeeType type);
}
