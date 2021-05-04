package ru.destered.semestr3sem.services.interfaces;

import ru.destered.semestr3sem.dto.SmsInfo;

public interface SmsConfirmService {

    String sendSms(String phone, String text);

    String checkSmsStatus(SmsInfo smsInfo);

}
