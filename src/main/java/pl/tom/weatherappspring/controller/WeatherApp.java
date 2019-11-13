package pl.tom.weatherappspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tom.weatherappspring.model.Weather;
import pl.tom.weatherappspring.rest.CurrentWeather;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Controller
public class WeatherApp {

    @Autowired
    CurrentWeather currentWeather;

    @GetMapping("/app")
    public String weatherApp(){

        return "index";
    }

    @GetMapping("/weather")
    public String weather(@RequestParam(value = "city", required = false) String city, Model model) throws IOException {
        Weather weather = currentWeather.get(city);
        LocalTime sunrise = getSunrise(weather);
        LocalTime sunset = getSunset(weather);

        String iconUrl = "http://openweathermap.org/img/wn/"+weather.getWeather().get(0).getIcon()+"@2x.png";


        model.addAttribute("weather",weather);
        model.addAttribute("sunrise", sunrise);
        model.addAttribute("sunset", sunset);
        model.addAttribute("iconUrl", iconUrl);

        return "weather";
    }

    private LocalTime getSunrise(Weather weather) {
        Integer seconds = weather.getSys().getSunrise();
        LocalDateTime sunriseUnix = LocalDateTime.ofEpochSecond(seconds,0, ZoneOffset.ofTotalSeconds(weather.getTimezone()));
        return LocalTime.of(sunriseUnix.getHour(), sunriseUnix.getMinute());
    }

    private LocalTime getSunset(Weather weather) {
        Integer seconds = weather.getSys().getSunset();
        LocalDateTime sunsetUnix = LocalDateTime.ofEpochSecond(seconds,0, ZoneOffset.ofTotalSeconds(weather.getTimezone()));
        return LocalTime.of(sunsetUnix.getHour(), sunsetUnix.getMinute());
    }
}
