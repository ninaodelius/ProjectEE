package se.nina.projectee.flash.weatherapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    private final WeatherWebClient weatherWebClient;

    public WeatherController(WeatherWebClient weatherWebClient) {
        this.weatherWebClient = weatherWebClient;
    }

    @GetMapping("/weather")
    public Mono<Weather> fetchWeather(){
        Mono<Weather> weather = weatherWebClient.webClient
                .get()
                .retrieve()
                .bodyToMono(Weather.class)
                .map(weatherRequest -> {
                    System.out.println(weatherRequest);
                    return weatherRequest;
                });
        return weather;
    }
}
