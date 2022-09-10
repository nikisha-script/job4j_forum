package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;

@Controller
public class IndexControl {

    private final PostService service;

    public IndexControl(PostService posts) {
        this.service = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("posts", service.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        post.setCreated(LocalDateTime.now());
        service.save(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", service.findById(id));
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "update";
    }

    @PostMapping("/update")
    public String getPost(@ModelAttribute Post post,
                          @RequestParam("id") int id) {
        post.setCreated(LocalDateTime.now());
        service.update(id, post);
        return "redirect:/index";
    }

    @GetMapping("/discussion/{id}")
    public String getDiscussion(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("p", service.findById(id));
        return "post";
    }


}
