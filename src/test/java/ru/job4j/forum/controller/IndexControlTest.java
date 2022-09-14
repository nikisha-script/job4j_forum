package ru.job4j.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class IndexControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMsgWithOnlySlash() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void whenReturnGetCreated() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("create"));
    }

    @Test
    @WithMockUser
    public void whenGetPost() throws Exception {
        Optional<Post> post = Optional.of(new Post("test", "test"));
        post.get().setId(1);
        post.get().setCreated(LocalDateTime.now());

        this.mockMvc.perform(get("/posts/{id}", post.get().getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("update"));
    }

    @Test
    @WithMockUser
    public void whenGetDiscussion() throws Exception {
        Optional<Post> post = Optional.of(new Post("test", "test"));
        post.get().setId(1);
        post.get().setCreated(LocalDateTime.now());

        this.mockMvc.perform(get("/discussion/{id}", post.get().getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

}