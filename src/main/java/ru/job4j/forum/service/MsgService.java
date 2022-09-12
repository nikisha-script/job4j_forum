package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.store.MsgRepo;

import java.util.List;

@Service
public class MsgService {

    private final MsgRepo msgRepo;

    public MsgService(MsgRepo msgRepo) {
        this.msgRepo = msgRepo;
    }

    public void addMsg(Message message) {
        msgRepo.save(message);
    }

    public List<Message> findById(int id) {
        return msgRepo.findById(id);
    }
}
