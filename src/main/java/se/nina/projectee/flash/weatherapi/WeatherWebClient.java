package se.nina.projectee.flash.weatherapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherWebClient {
    WebClient webClient = WebClient.create("https://goweather.herokuapp.com/weather/stockholm");
}
