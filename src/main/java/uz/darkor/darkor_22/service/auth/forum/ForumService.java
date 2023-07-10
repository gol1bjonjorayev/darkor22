package uz.darkor.darkor_22.service.auth.forum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.auth.forum.ForumCriteria;
import uz.darkor.darkor_22.dto.auth.forum.ForumCreateDTO;
import uz.darkor.darkor_22.dto.auth.forum.ForumGetDTO;
import uz.darkor.darkor_22.entity.auth.Forum;
import uz.darkor.darkor_22.mapper.auth.forum.ForumMapper;
import uz.darkor.darkor_22.repository.auth.forum.ForumRepository;
import uz.darkor.darkor_22.service.AbstractService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ForumService extends AbstractService<ForumMapper, ForumRepository> {
    public ForumService(ForumMapper mapper, ForumRepository repository) {
        super(mapper, repository);
    }

    public ForumGetDTO create(ForumCreateDTO dto) {
        Forum forum = new Forum();
        forum.setFullName(dto.getFullName());
        forum.setEmail(dto.getEmail());
        forum.setPhone(dto.getPhone());
        forum.setMessage(dto.getMessage());
        repository.save(forum);
        return mapper.toGetDTO(forum);
    }

    public List<ForumGetDTO> list(ForumCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Forum> list = repository.findAllOrderByCreatedAtDesc();
        Page<Forum> forums = new PageImpl<>(list, request, repository.count());
        return mapper.toListDTO(forums.getContent());
    }

    public Long getSize() {
        return repository.count();
    }
}
