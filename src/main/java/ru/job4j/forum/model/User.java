package ru.job4j.forum.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private int id;

    @NonNull
    private String username;
    @NonNull
    private String password;

}
