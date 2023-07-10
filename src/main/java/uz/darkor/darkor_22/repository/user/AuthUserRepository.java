package uz.darkor.darkor_22.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.darkor.darkor_22.entity.auth.AuthUser;
import uz.darkor.darkor_22.repository.BaseRepository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {

//    AuthUser findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from auth_user where active =?1 and username = ?2 and block=false")
    AuthUser find(boolean active, String username);
}
