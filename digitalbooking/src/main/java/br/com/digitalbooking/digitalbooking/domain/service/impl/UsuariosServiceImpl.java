package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.exception.RegisteredUserException;
import br.com.digitalbooking.digitalbooking.domain.repository.UsuariosRepository;
import br.com.digitalbooking.digitalbooking.domain.service.UsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuarios criarUsuario(Usuarios usuarios) {
        Optional<Usuarios> usuario = usuariosRepository.findUsuarioByEmail(usuarios.getEmail());

        if (usuario.isPresent()){
                throw new RegisteredUserException(usuarios.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(usuarios.getSenha());
        usuarios.setSenha(encodedPassword);
        Usuarios usuarioNovo = usuariosRepository.save(usuarios);

        Usuarios usuarioSalvo = usuarioNovo;
        usuarioNovo.setSenha("Senha oculta");
        return usuarioSalvo;

    }

    @Override
    public Usuarios buscarUsuarioPorId(UUID id) {
        try {
            return usuariosRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }

    @Override
    public Usuarios buscarUsuarioPorEmail(String email){

        return usuariosRepository.findUsuarioByEmail(email).orElseThrow();

    }

    @Override
    public List<Usuarios> buscarTodosUsuarios() {
            return usuariosRepository.findAll();
        }


}




