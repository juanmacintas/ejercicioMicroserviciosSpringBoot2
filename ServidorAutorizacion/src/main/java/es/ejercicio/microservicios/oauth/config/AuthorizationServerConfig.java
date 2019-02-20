package es.ejercicio.microservicios.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

	@Qualifier("authenticationManagerBean")
	@Autowired
	private AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception {

        log.info("Start {} :: endpoints -> {}", this.getClass().getName(),
            endpoints.toString());
    	endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore)
            .authenticationManager(authenticationManager);

        endpoints.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
        throws Exception {

        log.info("Start {} :: clients -> {}", this.getClass().getName(),
            clients.toString());

        clients.jdbc(dataSource);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

        log.info("Start {} :: oauth server -> {}", this.getClass().getName(),
            oauthServer.toString());

        // oauthServer.tokenKeyAccess("permitAll()")
        // .checkTokenAccess("isAuthenticated()");

        oauthServer.allowFormAuthenticationForClients();
    }



}
