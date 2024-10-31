package com.example.my_batis_test.mapper;

import com.example.my_batis_test.model.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("INSERT INTO posts(user_id, title, content) VALUES(#{userId}, #{title}, #{content})")
    void insertPost(Post post);

    @Select("SELECT * FROM posts WHERE user_id = #{userId}")
    List<Post> getPostsByUserId(Long userId);

    @Select("SELECT p.*, u.name AS user_name FROM posts p JOIN users u ON p.user_id = u.id WHERE p.id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "userName", column = "user_name") // Add a property for the user's name if needed
    })
    Post getPostWithUser(Long id);

}
