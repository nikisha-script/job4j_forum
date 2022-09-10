package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostStore;

import java.util.List;

@Service
public class PostService {

    private final PostStore store;

    public PostService(PostStore store) {
        this.store = store;
    }

    public List<Post> getAll() {
        return store.getAll();
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void save(Post post) {
        store.save(post);
    }

    public void update(int id, Post post) {
        store.update(id, post);
    }
}
