package uz.darkor.darkor_22.repository.auth.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.darkor.darkor_22.entity.auth.Forum;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long>, BaseRepository {
    @Query(value = "from Forum order by createdAt desc ")
    List<Forum> findAllOrderByCreatedAtDesc();
}
