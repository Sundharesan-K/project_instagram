package com.app.instagram.service.impl;

import static com.app.instagram.entity.LikeType.DISLIKE;
import static com.app.instagram.entity.LikeType.LIKE;

import com.app.instagram.dao.LikeDao;
import com.app.instagram.dao.PostDao;
import com.app.instagram.dao.UserDao;
import com.app.instagram.dao.UserProfileDao;
import com.app.instagram.dto.LikeDto;
import com.app.instagram.entity.LikeAndDisLike;
import com.app.instagram.entity.Post;
import com.app.instagram.entity.User;
import com.app.instagram.entity.UserProfile;
import com.app.instagram.service.LikeService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeDao likeDao;
    private final PostDao postDao;
    private final UserProfileDao userProfileDao;
    private final UserProfileServiceImpl userProfileService;
    private final UserDao userDao;

    @Override
    public void likeAndDisPost(LikeDto likeDto, String username) throws Exception {
        Post post = postDao.findById(likeDto.getPostId());
        Optional<User> user = userDao.findByUsername(username);
        try {
            if (user.isPresent()) {
                if (Objects.nonNull(post)) {
                    LikeAndDisLike likePost = likeDao.findByPostIdAndUsername(
                        likeDto.getPostId(), username);
                    if (Objects.nonNull(likePost) && likePost.getLikeType()
                        .equals(likeDto.getLikeType())) {
                        throw new Exception("You already exists the like list");
                    }
                    UserProfile userProfile = userProfileDao.findById(post.getUserId());
                    Optional<Post> post1 = userProfile.getPostList().stream()
                        .filter(id -> likeDto.getPostId().equals(id.getId()))
                        .findFirst();
                    updatePostList(likeDto, username, post, post1);
                    likeDao.save(mapToLike(likeDto, post, username));
                    postDao.save(post);
                    userProfileDao.save(userProfile);

                } else {
                    throw new Exception("Post is not found");
                }
            } else {
                throw new Exception("User is not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void updatePostList(LikeDto likeDto, String username, Post post,
        Optional<Post> post1) {
        if (LIKE.equals(likeDto.getLikeType())) {
            post.setLikeCount(post.getLikeCount() + 1);
            post.setLikedUsername(Arrays.asList(username));
            post.setUpdate_ts(LocalDateTime.now());
            post1.get().setLikeCount(post1.get().getLikeCount() + 1);
            post1.get().setLikedUsername(Arrays.asList(username));
            post1.get().setUpdate_ts(LocalDateTime.now());
        } else {
            post.setDisLikeCount(post.getDisLikeCount() + 1);
            post.setDisLikedUsername(Arrays.asList(username));
            post.setUpdate_ts(LocalDateTime.now());
            post1.get().setDisLikeCount(post1.get().getLikeCount() + 1);
            post1.get().setDisLikedUsername(Arrays.asList(username));
            post1.get().setUpdate_ts(LocalDateTime.now());
        }
    }

    private LikeAndDisLike mapToLike(LikeDto likeDto, Post post, String username) {
        return LikeAndDisLike.builder()
            .likeType(likeDto.getLikeType())
            .username(username)
            .postId(post.getId())
            .create_ts(LocalDateTime.now())
            .build();
    }
}
