package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        MessageSenderImpl messageSender = Mockito.mock(MessageSenderImpl.class);
        when(messageSender.send(headers)).thenReturn(localizationService.locale(Country.RUSSIA));

        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }
}