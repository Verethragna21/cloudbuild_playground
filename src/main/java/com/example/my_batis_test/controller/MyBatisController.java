package com.example.my_batis_test.controller;

import com.example.my_batis_test.mapper.PostMapper;
import com.example.my_batis_test.mapper.UserMapper;
import com.example.my_batis_test.model.Post;
import com.example.my_batis_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/my-batis", produces = MediaType.APPLICATION_JSON_VALUE)
public class MyBatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userMapper.insertUser(user);
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody Post post) {
        postMapper.insertPost(post);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userMapper.getUserById(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserWithPosts(@PathVariable Long id) {
        User user = userMapper.getUserWithPosts(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userMapper.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userMapper.deleteUser(id);
    }

}
