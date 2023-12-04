package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.SingIn;
import br.com.digitalbooking.digitalbooking.domain.entity.SingUp;
import br.com.digitalbooking.digitalbooking.domain.repository.UserRepository;
import br.com.digitalbooking.digitalbooking.domain.service.AuthenticationUserService;
import br.com.digitalbooking.digitalbooking.domain.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public String signIn(SingIn request) {
        return null;
    }

    @Override
    public String signUp(SingUp request) {
        return null;
    }
}
