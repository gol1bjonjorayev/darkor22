package uz.darkor.darkor_22.repository.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.darkor.darkor_22.entity.home.HomeService;
import uz.darkor.darkor_22.entity.home.Post;
import uz.darkor.darkor_22.enums.PostType;
import uz.darkor.darkor_22.repository.BaseRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, BaseRepository {

    Optional<Post> findByIdAndPostType(Long id, PostType postType);

    List<Post> findByPostType(PageRequest request, PostType postType);
}
