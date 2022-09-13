package ru.job4j.forum.controller;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MsgService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.store.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService posts;

    @MockBean
    private MsgService msgService;

    @MockBean
    private UserRepository users;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    @Ignore
    public void whenPostPost() throws Exception {
        this.mockMvc.perform(post("/update")
                        .param("name","Куплю ладу-грант. Дорого.")
                        .param("description", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    @Ignore
    public void whenPostComment() throws Exception {
        Optional<Post> post = Optional.of(new Post("test", "test"));
        post.get().setId(1);
        post.get().setCreated(LocalDateTime.now());
        this.mockMvc.perform(post("/comment/{id}", post.get().getId())
                        .param("text", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/discussion/1"));
        ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        argument.getValue().setPost(post.get());
        post.get().getMessages().add(argument.getValue());
        verify(msgService).addMsg(argument.capture());
        assertThat(argument.getValue().getText(), is("test"));
    }

    @Test
    @WithMockUser
    public void whenPostUserSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "test")
                        .param("password", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        ArgumentCaptor<User>  argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(users).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getUsername(), is("test"));
    }

}
