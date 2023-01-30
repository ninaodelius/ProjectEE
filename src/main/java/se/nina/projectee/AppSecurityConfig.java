package se.nina.projectee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import se.nina.projectee.flash.FlashModelDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    private final FlashModelDetailsService flashModelDetailsService;

    @Autowired
    public AppSecurityConfig(FlashModelDetailsService flashModelDetailsService){
        this.flashModelDetailsService = flashModelDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests( requests -> {
                    requests.requestMatchers("/home").permitAll();
                });

        return http.build();
    }

}