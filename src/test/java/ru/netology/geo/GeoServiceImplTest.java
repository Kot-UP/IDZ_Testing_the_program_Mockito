package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.mockito.Mockito.when;

class GeoServiceImplTest {

    @Test
    void byIp() {
        String ip = "96.44.183.149";

        GeoServiceImpl geoService = new GeoServiceImpl();
        Location locations = Mockito.mock(Location.class);
        when(locations.getCountry()).thenReturn(geoService.byIp(ip).getCountry());
        when(locations.getCity()).thenReturn(geoService.byIp(ip).getCity());

        Assertions.assertEquals("New York", locations.getCity());
    }
}