package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository store;

    public PostService(PostRepository store) {
        this.store = store;
    }

    public List<Post> getAll() {
        return store.findAll();
    }

    public Optional<Post> findById(int id) {
        return store.findById(id);
    }

    public void save(Post post) {
        store.save(post);
    }

}
