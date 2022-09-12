package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Message;

import java.util.List;

@Repository
public interface MsgRepo extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.post.id = :id")
    List<Message> findById(@Param("id") int id);

}
