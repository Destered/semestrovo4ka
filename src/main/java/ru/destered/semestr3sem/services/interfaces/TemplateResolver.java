package ru.destered.semestr3sem.services.interfaces;

import java.util.Map;

public interface TemplateResolver {
    String process(String name, Map<String, String> root);
}