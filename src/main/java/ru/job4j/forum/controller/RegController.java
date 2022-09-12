package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.RoleRepository;
import ru.job4j.forum.store.UserRepository;

import java.util.Optional;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final RoleRepository rule;

    public RegController(PasswordEncoder encoder, UserRepository users, RoleRepository rule) {
        this.encoder = encoder;
        this.users = users;
        this.rule = rule;
    }

    @GetMapping("/reg")
    public String regPage(Model model, @RequestParam(name = "error", required = false) String errorMessage) {
        String errorMsg = null;
        if (errorMessage != null) {
            errorMsg = "Пользователь с таким ником уже существует";
        }
        model.addAttribute("errorMessage", errorMsg);
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        Optional<User> userDb = users.findByUsername(user.getUsername());
        if (userDb.isPresent()) {
            return "redirect:/reg?error=true";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(rule.findByRole("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

}
