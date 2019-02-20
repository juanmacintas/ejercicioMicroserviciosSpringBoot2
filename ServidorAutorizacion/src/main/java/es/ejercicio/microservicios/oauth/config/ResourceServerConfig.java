package es.ejercicio.microservicios.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableResourceServer
@Configuration
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter  {

	  @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.anonymous().disable()
	             .authorizeRequests()
	             .antMatchers("/biblioteca/**").authenticated()
	             .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	    }

}
