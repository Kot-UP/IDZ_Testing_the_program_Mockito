package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;


class MessageSenderImplTest {

    @Test
    void sendRu() {
        Location location = Mockito.mock(Location.class);
        when(location.getCountry()).thenReturn(Country.RUSSIA);

        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp("172.123.12.19")).thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn(" Добро пожаловать! ");
        when(localizationService.locale(Country.USA)).thenReturn(" Welcome! ");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Assertions.assertEquals(" Добро пожаловать! ", messageSender.send(headers));
    }
}