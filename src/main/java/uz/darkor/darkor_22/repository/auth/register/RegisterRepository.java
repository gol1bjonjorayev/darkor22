package uz.darkor.darkor_22.repository.auth.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.darkor.darkor_22.entity.auth.Register;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>, BaseRepository {
    @Query(value = "from Register order by createdAt desc ")
    List<Register> findAllOrderByCreatedAtDesc();
}
