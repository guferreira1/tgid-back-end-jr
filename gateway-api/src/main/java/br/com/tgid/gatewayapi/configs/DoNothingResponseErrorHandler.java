package br.com.tgid.gatewayapi.configs;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class DoNothingResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(final ClientHttpResponse response) throws IOException {
        //Não faz nada, assim consegue pegar o erro que está vindo do response
    }

}