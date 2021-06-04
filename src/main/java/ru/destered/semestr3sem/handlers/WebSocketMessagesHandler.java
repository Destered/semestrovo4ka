package ru.destered.semestr3sem.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.destered.semestr3sem.models.ChatStorage;
import ru.destered.semestr3sem.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();
    ChatStorage messageStorage = new ChatStorage();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonMessage) throws Exception {
        Message message = objectMapper.readValue(jsonMessage.getPayload(), Message.class);


        if (message.getFrom().equals("-1")){
            for(TextMessage jsonMessageTemp: messageStorage.getNewMessage()){
                session.sendMessage(jsonMessageTemp);
            }
        }else {
            if (!sessions.containsKey(message.getFrom())) {
                sessions.put(message.getFrom(), session);
            }

            try {
                for (WebSocketSession currentSession : sessions.values()) {
                    currentSession.sendMessage(jsonMessage);
                }
            } catch (IllegalStateException is) {
                sessions.clear();
            }

            messageStorage.addMessage(jsonMessage);
        }
    }
}

