package ru.job4j.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode
public class Post {

    private int id;
    private String name;
    private String description;
    private Calendar created;

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }


}
