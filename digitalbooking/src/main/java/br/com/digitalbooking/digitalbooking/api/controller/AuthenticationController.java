package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.AuthenticationDTO;
import br.com.digitalbooking.digitalbooking.api.dto.request.RegisterDTO;
import br.com.digitalbooking.digitalbooking.domain.repository.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository repository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var emailsenha = new UsernamePasswordAuthenticationToken(data.email(),data.senha());
        var auth = this.authenticationManager.authenticate(emailsenha);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedSenha = new BCryptPasswordEncoder().encode(data.senha());
        return null;
    }

}
