package es.ejercicio.microservicios.libros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class LibrosResourceServerConfig  extends ResourceServerConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                	antMatchers(AUTH_WHITELIST).permitAll().
                	and()
             .authorizeRequests().
                	antMatchers("/libros/**").authenticated().
                    antMatchers("/categorias/**").authenticated().
                    antMatchers("/editoriales/**").authenticated().
        			antMatchers("/autores/**").authenticated();
    }

}
