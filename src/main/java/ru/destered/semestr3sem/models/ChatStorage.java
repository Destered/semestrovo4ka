package ru.destered.semestr3sem.models;

import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatStorage {
    int lastFreeIndex = 0;
    boolean firstIter = true;
    private  List<TextMessage> messageStorage = new ArrayList<>();

    public int addMessage(TextMessage message) {
        if (lastFreeIndex == 100) {
            lastFreeIndex = 0;
            List<TextMessage> newStorage = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                newStorage.add(messageStorage.get(80+i));
            }
            messageStorage = newStorage;
        }
        if (firstIter) {
            messageStorage.add(message);
        } else {
            messageStorage.set(lastFreeIndex, message);
        }
        lastFreeIndex++;
        return lastFreeIndex;
    }

    public List<TextMessage> getNewMessage() {
        return messageStorage;
    }
}
