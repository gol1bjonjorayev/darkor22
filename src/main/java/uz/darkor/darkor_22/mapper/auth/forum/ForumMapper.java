package uz.darkor.darkor_22.mapper.auth.forum;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.darkor.darkor_22.dto.auth.forum.ForumCreateDTO;
import uz.darkor.darkor_22.dto.auth.forum.ForumGetDTO;
import uz.darkor.darkor_22.dto.auth.forum.ForumUpdateDTO;
import uz.darkor.darkor_22.entity.auth.Forum;
import uz.darkor.darkor_22.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface ForumMapper extends GenericMapper<ForumCreateDTO, ForumUpdateDTO, ForumGetDTO, Forum> {
}
