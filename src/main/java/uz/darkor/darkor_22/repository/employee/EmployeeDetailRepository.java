package uz.darkor.darkor_22.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.darkor.darkor_22.entity.auth.Employee;
import uz.darkor.darkor_22.entity.auth.EmployeeDetail;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long>, BaseRepository {
    Optional<EmployeeDetail> findByCode(UUID code);

    boolean deleteByCode(UUID code);

    Optional<EmployeeDetail> findByEmployee(Employee employee);

    @Query(value = "select ed.* from employee_detail ed inner join employee e on e.id = ed.employee_id inner join employee_courses ec on e.id = ec.employee_id and ec.courses_id = :param and e.type ='EXPERT'", nativeQuery = true)
    List<EmployeeDetail> findAllByCourse(@Param("param") Long id);
}
