package pl.tom.weatherappspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tom.weatherappspring.model.currentWeather.Weather;
import pl.tom.weatherappspring.model.forecastWeather.ForecastWeather;
import pl.tom.weatherappspring.rest.CurrentWeatherRest;
import pl.tom.weatherappspring.rest.ForecastRest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Controller
public class WeatherController {

    @Autowired
    CurrentWeatherRest currentWeatherRest;
    @Autowired
    ForecastRest forecastRest;

    @GetMapping("/weather")
    public String weather(@RequestParam(value = "city", required = false) String city, Model model) throws IOException {
        Weather weather = currentWeatherRest.get(city);
        LocalTime sunrise = getSunrise(weather);
        LocalTime sunset = getSunset(weather);
        String iconUrl = "http://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@2x.png";

        ForecastWeather forecastWeather = forecastRest.get(city);

        double rain = getRain(weather);

        model.addAttribute("weather", weather);
        model.addAttribute("sunrise", sunrise);
        model.addAttribute("sunset", sunset);
        model.addAttribute("iconUrl", iconUrl);
        model.addAttribute("rain", rain);
        model.addAttribute("forecastWeather", forecastWeather);


        return "weather";
    }

    public double getRain(Weather weather) {
        double rain;
        if (weather.getRain() == null) {
            rain = 0;
        } else {
            if (weather.getRain().get1h() != null) {
                rain = weather.getRain().get1h();
            } else if (weather.getRain().get2h() != null) {
                rain = weather.getRain().get2h();
            } else if (weather.getRain().get3h() != null) {
                rain = weather.getRain().get3h();
            } else if (weather.getRain().get4h() != null) {
                rain = weather.getRain().get4h();
            } else if (weather.getRain().get5h() != null) {
                rain = weather.getRain().get5h();
            } else {
                rain = 0;
            }
        }
        return rain;
    }

    public LocalTime getSunrise(Weather weather) {
        Integer seconds = weather.getSys().getSunrise();
        LocalDateTime sunriseUnix = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.ofTotalSeconds(weather.getTimezone()));
        return LocalTime.of(sunriseUnix.getHour(), sunriseUnix.getMinute());
    }

    public LocalTime getSunset(Weather weather) {
        Integer seconds = weather.getSys().getSunset();
        LocalDateTime sunsetUnix = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.ofTotalSeconds(weather.getTimezone()));
        return LocalTime.of(sunsetUnix.getHour(), sunsetUnix.getMinute());
    }

}
