package br.com.tgid.gatewayapi.configs;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {


    @Bean
    public HttpClient httpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory(final HttpClient httpClient) {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    @Bean
    @Primary
    public RestTemplate restTemplate(final ClientHttpRequestFactory requestFactory) {
        final RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new DoNothingResponseErrorHandler());
        return restTemplate;
    }
}
