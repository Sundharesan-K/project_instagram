package com.app.instagram.mapper;

import com.app.instagram.dto.PostDTO;
import com.app.instagram.entity.Post;
import java.time.LocalDateTime;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Mapping(target = "userId",source = "userId")
//    @Mapping(target = "create_ts", expression = "java.LocalDateTime.now()")
//    @Mapping(target = "update_ts", expression = "java.LocalDateTime.now()")
    public abstract Post mapToPost(PostDTO postDTO,String userId);

    public abstract PostDTO mapToPostDTO(Post post);

    @BeforeMapping
    public void setDates(PostDTO postDTO, @MappingTarget Post post) {
        post.setCreate_ts(LocalDateTime.now());
        post.setUpdate_ts(LocalDateTime.now());
    }
}
