package ru.job4j.forum.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class IndexControl {

    private final PostService service;

    public IndexControl(PostService posts) {
        this.service = posts;
    }


    @GetMapping({"/", "/index"})
    public String index(Model model,
                        @CurrentSecurityContext(expression = "authentication") Principal principal) {
        model.addAttribute("user", principal.getName());
        model.addAttribute("posts", service.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String getCreate(Model model,
                            @CurrentSecurityContext(expression = "authentication") Principal principal) {
        model.addAttribute("user", principal.getName());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        post.setCreated(LocalDateTime.now());
        service.save(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") int id, Model model,
                          @CurrentSecurityContext(expression = "authentication") Principal principal) {
        model.addAttribute("post", service.findById(id).get());
        model.addAttribute("user", principal.getName());
        return "update";
    }

    @PostMapping("/update")
    public String getPost(@ModelAttribute Post post,
                          @RequestParam("id") int id) {
        post.setCreated(LocalDateTime.now());
        Post rsl = service.findById(id).get();
        rsl.setName(post.getName());
        rsl.setDescription(post.getDescription());
        rsl.setCreated(post.getCreated());
        service.save(rsl);
        return "redirect:/index";
    }

    @GetMapping("/discussion/{id}")
    public String getDiscussion(@PathVariable("id") int id, Model model,
                                @CurrentSecurityContext(expression = "authentication") Principal principal) {
        model.addAttribute("user", principal.getName());
        model.addAttribute("p", service.findById(id).get());
        return "post";
    }


}
