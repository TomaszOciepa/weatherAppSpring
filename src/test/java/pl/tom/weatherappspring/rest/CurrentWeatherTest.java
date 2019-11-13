package pl.tom.weatherappspring.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tom.weatherappspring.model.Weather;

import java.io.IOException;

import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherTest {

    @Spy
    CurrentWeather currentWeather;

    @Before
    public void init() throws IOException {
        String city = "Gdańsk";
        given(currentWeather.get(city)).willReturn(prepareMockData());
    }

    private Weather prepareMockData() {
        Weather weather = new Weather();
        weather.setName("Gdańsk");
        return weather;
    }

    @Test
    public void get() throws IOException {
        //when
        String city = "Gdańsk";
        Weather weather = currentWeather.get(city);
        //then
        Assert.assertEquals(weather.getName(), "Gdańsk");
        Assert.assertNotEquals(weather.getName(), "Gdynia");
    }
}