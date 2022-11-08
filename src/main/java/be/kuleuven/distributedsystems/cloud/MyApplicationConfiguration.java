package be.kuleuven.distributedsystems.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class MyApplicationConfiguration {

    @Bean
    public WebClient myWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("https://reliable-airline.com")
                .build();
    }
}
