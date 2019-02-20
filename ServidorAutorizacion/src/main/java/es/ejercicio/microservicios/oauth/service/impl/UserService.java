package es.ejercicio.microservicios.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.ejercicio.microservicios.oauth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class UserService
    implements UserDetailsService {

    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
    	log.debug("Inicio loadUserByUsername:" + username);
        UserDetails user = userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        log.debug("Fin loadUserByUsername:" + user);
        return user;
    }

}
