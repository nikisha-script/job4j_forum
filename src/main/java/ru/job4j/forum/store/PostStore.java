package ru.job4j.forum.store;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private final List<Post> posts = new ArrayList<>();
    private AtomicInteger id;

    public PostStore() {
        this.id = new AtomicInteger(0);
        Post post = new Post("Продаю машину ладу 01.", "2015 год");
        post.setId(id.getAndIncrement());
        posts.add(post);
    }

    public List<Post> getAll() {
        return posts;
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void save(Post post) {
        post.setId(id.getAndIncrement());
        posts.add(post);
    }

    public void update(int id, Post post) {
        posts.set(id, post);
    }
}
