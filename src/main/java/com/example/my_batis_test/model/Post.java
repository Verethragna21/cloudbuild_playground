package com.example.my_batis_test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String content;

}
