package se.nina.projectee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import se.nina.projectee.flash.FlashModelDetailsService;

import java.util.concurrent.TimeUnit;

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
                    requests.requestMatchers("/", "/login", "/signup", "/logout", "/static/**", "/save").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN")
                            .requestMatchers("/flash").hasRole("FLASH")
                            .anyRequest()
                            .authenticated()
                            ;
                })
                .formLogin( formlogin ->{
                            formlogin.loginPage("/login");
                        }
                )
                .rememberMe( rememberMe -> {
                            rememberMe.rememberMeParameter("remember-me-token")
                                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // 3 weeks
                                    .key("SomeSecureKey")
                                    .userDetailsService(flashModelDetailsService);
                        }
                )
                .authenticationProvider(authenticationOverride());
        return http.build();
    }

    @Autowired
    public DaoAuthenticationProvider authenticationOverride(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(flashModelDetailsService);
        return provider;
    }

}
