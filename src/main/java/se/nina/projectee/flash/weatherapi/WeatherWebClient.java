package se.nina.projectee.flash.weatherapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WeatherWebClient {

    WebClient webClient = WebClient.create("https://goweather.herokuapp.com/weather/stockholm");

    public Mono<Weather> getWeatherInfo(){
        Mono<Weather> weather = webClient
                .get()
                .retrieve()
                .bodyToMono(Weather.class)
                .map(weatherRequest -> {
                    System.out.println(weatherRequest);
                    return weatherRequest;
                });
        return weather;
    }

    public Flux<Weather> monoToFlux(){
        return getWeatherInfo().flux();
    }

    public List<Weather> fluxToList(){
    return monoToFlux().collectList().block();
    }

}
