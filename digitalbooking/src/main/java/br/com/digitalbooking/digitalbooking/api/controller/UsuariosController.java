package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.UsuariosRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.UsuariosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.UsuariosListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.UsuariosWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;
import br.com.digitalbooking.digitalbooking.domain.service.UsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("v1/usuarios")
@Tag(name = "Usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final ObjectMapper objectMapper;


    @Autowired
    public UsuariosController(UsuariosService UsuariosService, ObjectMapper objectMapper) {
        this.usuariosService = UsuariosService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<UsuariosResponse> criarUsuario(@RequestBody @Valid UsuariosRequest request) {


        Usuarios usuario = objectMapper.convertValue(request, Usuarios.class);

        String criptopassword = CriptoPassword(usuario.getSenha());

        usuario.setSenha(criptopassword);

        Usuarios UsuarioCriado = usuariosService.criarUsuario(usuario);
        UsuariosResponse UsuariosResponse = objectMapper.convertValue(UsuarioCriado, UsuariosResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuariosResponse);
    }

    private String CriptoPassword(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }


    @GetMapping("{id}")
    ResponseEntity<UsuariosResponse>buscarUsuarioPorId(@PathVariable UUID id) {

        Usuarios usuarios = usuariosService.buscarUsuarioPorId(id);
        UsuariosResponse response = usuariosResponseByUsuarios(usuarios);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    ResponseEntity<UsuariosWrapperResponse>buscarTodosUsuarios(){
        List<Usuarios> usuarios = usuariosService.buscarTodosUsuarios();
        UsuariosWrapperResponse usuariosWrapperResponse = new UsuariosWrapperResponse();

        usuariosWrapperResponse.setUsuarios(usuarios.stream().map( usuario ->{
            UsuariosListResponse usuariosResponse = new UsuariosListResponse();

            usuariosResponse.setId(usuario.getId());
            usuariosResponse.setNome(usuario.getNome());
            usuariosResponse.setSobrenome(usuario.getSobrenome());
            usuariosResponse.setEmail(usuario.getEmail());
            usuariosResponse.setSenha(usuario.getSenha());

            return usuariosResponse;

        }).toList());

        return ResponseEntity.ok(usuariosWrapperResponse);

    }

    private UsuariosResponse usuariosResponseByUsuarios (Usuarios usuarios){
        UsuariosResponse usuariosResponse = new UsuariosResponse();
        usuariosResponse.setId(usuarios.getId());
        usuariosResponse.setNome(usuarios.getNome());
        usuariosResponse.setSobrenome(usuarios.getSobrenome());
        usuariosResponse.setEmail(usuarios.getEmail());
        usuariosResponse.setSenha(usuarios.getSenha());

        return usuariosResponse;
    }

}
