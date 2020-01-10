package pl.tom.weatherappspring.rest;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pl.tom.weatherappspring.model.forecastWeather.ForecastWeather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class ForecastRest {

    public ForecastWeather get(String city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&APPID=5edc3ee7e769f70237953bb6d7580423");
        ForecastWeather weather = new ForecastWeather();

        try {
            InputStreamReader reader = new InputStreamReader(url.openStream());
            weather = new Gson().fromJson(reader, ForecastWeather.class);
            return weather;
        }catch (FileNotFoundException e){
            weather.setCod("404");
            return weather;
        }

    }
}
