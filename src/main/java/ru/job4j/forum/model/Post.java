package ru.job4j.forum.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {

    private int id;

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime created;


}