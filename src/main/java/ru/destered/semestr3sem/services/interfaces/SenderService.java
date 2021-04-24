package ru.destered.semestr3sem.services.interfaces;

public interface SenderService {
    void sendMessage(String subject, String email, String html);
}
