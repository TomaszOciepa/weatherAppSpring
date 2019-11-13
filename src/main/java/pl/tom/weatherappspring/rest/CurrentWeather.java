package pl.tom.weatherappspring.rest;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pl.tom.weatherappspring.model.Weather;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CurrentWeather {

    public Weather get(String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ city +",pl&units=metric&APPID=58fb0cad9835fc6ae2462d0a0bfa99c5");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        Weather weather = new Gson().fromJson(reader, Weather.class);
        return weather;
    }
}

