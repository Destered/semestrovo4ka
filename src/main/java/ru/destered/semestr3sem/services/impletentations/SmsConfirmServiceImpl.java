package ru.destered.semestr3sem.services.impletentations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.destered.semestr3sem.dto.SmsInfo;
import ru.destered.semestr3sem.services.interfaces.SmsConfirmService;

@Service
@RequiredArgsConstructor
public class SmsConfirmServiceImpl implements SmsConfirmService {
    private final RestTemplate restTemplate;

    @Value("${sms.base.url}")
    private String baseUrl;
    @Value("${sms.send.path}")
    private String sendSmsPath;
    @Value("${sms.check.path}")
    private String checkSmsPath;
    @Value("${api.key}")
    private String key;
    @Value("${sms.api.parameter}")
    private String apiParameterValue;
    @Value("${sms.receiver.key_start}")
    private String receiverKeyStart;
    @Value("${sms.receiver.key_end}")
    private String receiverKeyEnd;
    @Value("${sms.json.enabled.key}")
    private String jsonEnabledKey;

    @Override
    public String sendSms(String phone, String text) {
        String smsUrl = baseUrl
                + sendSmsPath
                + apiParameterValue + key
                + receiverKeyStart + phone + receiverKeyEnd
                + "=" + extractText(text) + jsonEnabledKey;
        return restTemplate.getForObject(smsUrl, String.class);
    }

    @Override
    public String checkSmsStatus(SmsInfo smsInfo) {
        String smsUrl = baseUrl
                + checkSmsPath
                + apiParameterValue + key
                + "&sms_id=" + extractIds(smsInfo.getId()) + jsonEnabledKey;
        return restTemplate.getForObject(smsUrl, String.class);
    }

    private String extractIds(String[] ids) {
        StringBuilder sb = new StringBuilder();
        sb.append(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            sb.append(",").append(ids[i]);
        }
        return sb.toString();
    }

    private String extractText(String text) {
        String[] result = text.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]);
        for (int i = 1; i < result.length ; i++) {
            sb.append("+").append(result[i]);
        }
        return sb.toString();
    }
}
