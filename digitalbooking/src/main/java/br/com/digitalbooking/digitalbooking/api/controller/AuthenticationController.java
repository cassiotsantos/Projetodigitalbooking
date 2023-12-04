package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.AuthenticationSingInDTO;
import br.com.digitalbooking.digitalbooking.api.dto.request.AuthenticationSingUpDTO;
import br.com.digitalbooking.digitalbooking.api.dto.response.AuthenticationResponseDTO;
import br.com.digitalbooking.digitalbooking.domain.entity.SingIn;
import br.com.digitalbooking.digitalbooking.domain.entity.SingUp;
import br.com.digitalbooking.digitalbooking.domain.repository.UserRepository;
import br.com.digitalbooking.digitalbooking.domain.repository.UsuariosRepository;
import br.com.digitalbooking.digitalbooking.domain.service.AuthenticationUserService;
import br.com.digitalbooking.digitalbooking.infra.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationUserService authenticationUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuariosRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationSingInDTO request){

        SingIn signIn = new SingIn(request.getEmail(), request.getSenha());
        String jwt = authenticationUserService.signIn(signIn);
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));

    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register (@RequestBody @Valid AuthenticationSingUpDTO request) throws Exception{

        SingUp signUp = SingUp.builder()
                .email(request.getEmail())
                .senha(request.getSenha())
                .role(request.getRole())
                .build();
        String jwt = authenticationUserService.signUp(signUp);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponseDTO(jwt));

    }

}
