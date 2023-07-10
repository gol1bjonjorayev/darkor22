package uz.darkor.darkor_22.repository.statistics;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.darkor.darkor_22.entity.home.Post;
import uz.darkor.darkor_22.entity.home.Statistics;
import uz.darkor.darkor_22.enums.PostType;
import uz.darkor.darkor_22.enums.StatisticsType;
import uz.darkor.darkor_22.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long>, BaseRepository {
    Optional<Statistics> findByIdAndStatisticsType(Long id, StatisticsType type);

    List<Statistics> findByStatisticsType(PageRequest request, StatisticsType type);
}
