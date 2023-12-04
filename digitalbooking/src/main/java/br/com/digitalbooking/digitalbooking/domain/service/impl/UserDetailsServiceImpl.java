package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.repository.UserRepository;
import br.com.digitalbooking.digitalbooking.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserService {
        private final UserRepository userRepository;
        @Override
        public UserDetailsService userDetailsService(){
             return (email)->
                     userRepository
                             .findByEmail(email)
                             .orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado"));
        }
}
