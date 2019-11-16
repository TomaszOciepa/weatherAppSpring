package pl.tom.weatherappspring.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tom.weatherappspring.model.*;
import pl.tom.weatherappspring.rest.CurrentWeather;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @Mock
    CurrentWeather currentWeather;

    @InjectMocks
    WeatherController weatherController;

    @Before
    public void init() throws IOException {
        given(weatherController.currentWeather.get("Warsaw")).willReturn(prepareMockData());
    }

    @Test
    public void shouldReturnSunrise() throws IOException {
        //when
        Weather weather = weatherController.currentWeather.get("Warsaw");
        LocalTime sunrise = weatherController.getSunrise(weather);
        Integer seconds = 1573797357;
        LocalDateTime sunriseUnix = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.ofTotalSeconds(3600));
        LocalTime expectedSunrise = LocalTime.of(sunriseUnix.getHour(), sunriseUnix.getMinute());
        //then
        Assert.assertEquals(expectedSunrise, sunrise);

    }

    @Test
    public void shouldReturnSunset() throws IOException {
        //when
        Weather weather = weatherController.currentWeather.get("Warsaw");
        LocalTime sunset = weatherController.getSunset(weather);
        Integer seconds = 1573829113;
        LocalDateTime sunsetUnix = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.ofTotalSeconds(3600));
        LocalTime expectedSunset = LocalTime.of(sunsetUnix.getHour(), sunsetUnix.getMinute());
        //then
        Assert.assertEquals(expectedSunset, sunset);

    }

    @Test
    public void shouldReturnRain() throws IOException {
        //when
        Weather weather = weatherController.currentWeather.get("Warsaw");
        double rain = weatherController.getRain(weather);
        double expectedRainTrue = 55.0;
        double expectedRainFalse = 35.0;
        //then
        Assert.assertEquals(expectedRainTrue, rain, 0);
        Assert.assertNotEquals(expectedRainFalse, rain, 0);

    }

    private Weather prepareMockData() {
        Weather weather = new Weather();
        weather.setTimezone(3600);
        weather.setId(756135);
        weather.setName("Warsaw");
        weather.setCod(200);
        Sys sys = new Sys();
        sys.setCountry("PL");
        sys.setType(1);
        sys.setId(1713);
        sys.setSunrise(1573797357);
        sys.setSunset(1573829113);
        weather.setSys(sys);
        weather.setDt(1573810804);
        Clouds clouds = new Clouds();
        clouds.setAll(75);
        weather.setClouds(clouds);
        Rain rain = new Rain();
        rain.set1h(55.0);
        weather.setRain(rain);
        Wind wind = new Wind();
        wind.setSpeed(5.7);
        wind.setDeg(120);
        weather.setWind(wind);
        return weather;
    }
}