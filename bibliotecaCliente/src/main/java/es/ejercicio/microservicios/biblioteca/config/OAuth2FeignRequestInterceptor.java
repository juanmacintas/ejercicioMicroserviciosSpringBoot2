package es.ejercicio.microservicios.biblioteca.config;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";


	private final OAuth2RestTemplate oAuth2RestTemplate;

	public OAuth2FeignRequestInterceptor(OAuth2RestTemplate restTemplate) {
	      this.oAuth2RestTemplate = restTemplate;
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		log.debug("REQUEST:" + requestTemplate.toString());
		log.debug("Se aplica el token:" + oAuth2RestTemplate.getAccessToken().toString());
		requestTemplate.header(AUTHORIZATION_HEADER,
                String.format("%s %s",
                		BEARER_TOKEN_TYPE,
                        oAuth2RestTemplate.getAccessToken().toString()));
	}
}
