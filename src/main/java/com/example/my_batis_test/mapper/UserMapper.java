package com.example.my_batis_test.mapper;

import com.example.my_batis_test.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(Long id);

    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "posts", column = "id",
                    many = @Many(select = "com.example.my_batis_test.mapper.PostMapper.getPostsByUserId"))
    })
    User getUserWithPosts(Long id);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);

}
