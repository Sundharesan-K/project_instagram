package com.app.instagram.service.impl;

import static com.app.instagram.constant.MessageConstants.USER_NOT_FOUND;

import com.app.instagram.dao.PostDao;
import com.app.instagram.dao.UserProfileDao;
import com.app.instagram.dto.PostDTO;
import com.app.instagram.entity.Post;
import com.app.instagram.entity.UserProfile;
import com.app.instagram.mapper.PostMapper;
import com.app.instagram.service.PostService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final UserProfileDao userProfileDao;
    private final PostMapper postMapper;

    @Override
    public PostDTO createPost(String userId, PostDTO postDTO) throws Exception {
        UserProfile userProfile = userProfileDao.findById(userId);
        try {
            if (Objects.nonNull(userProfile)) {
                Post post = postMapper.mapToPost(postDTO, userId);
                Post save = postDao.save(post);
                userProfile.setPost(save);
                userProfileDao.save(userProfile);
                return postDTO;
            } else {
                throw new Exception(USER_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
