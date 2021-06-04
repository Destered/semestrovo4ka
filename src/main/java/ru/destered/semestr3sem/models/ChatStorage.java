package ru.destered.semestr3sem.models;

import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatStorage {
    int lastFreeIndex = 0;
    boolean firstIter = true;
    private  List<TextMessage> chatStorage = new ArrayList<>();

    public int addMessage(TextMessage message) {
        if (lastFreeIndex == 100) {
            lastFreeIndex = 0;
            List<TextMessage> newStorage = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                newStorage.add(chatStorage.get(80+i));
            }
            chatStorage = newStorage;
        }
        if (firstIter) {
            chatStorage.add(message);
        } else {
            chatStorage.set(lastFreeIndex, message);
        }
        lastFreeIndex++;
        return lastFreeIndex;
    }

    public List<TextMessage> getNewMessage() {
        return chatStorage;
    }
}
