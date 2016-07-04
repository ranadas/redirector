package com.rdas.repo

import com.rdas.model.Message

/**
 * Created by rdas on 04/07/2016.
 */
interface MessageRepository {
    Iterable<Message> findAll();

    Message save(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);
}