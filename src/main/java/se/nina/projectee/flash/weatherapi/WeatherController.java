package se.nina.projectee.flash.weatherapi;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherWebClient weatherWebClient;

    public WeatherController(WeatherWebClient weatherWebClient) {
        this.weatherWebClient = weatherWebClient;
    }

    /*@GetMapping("/fetchWeather")
    public Mono<Weather> fetchWeather(){
       return weatherWebClient.getWeatherInfo();
    }*/

}
