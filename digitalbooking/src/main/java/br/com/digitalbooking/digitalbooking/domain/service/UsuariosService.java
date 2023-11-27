package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;

import java.util.List;
import java.util.UUID;

public interface UsuariosService {

    Usuarios criarUsuario (Usuarios usuarios);
    Usuarios buscarUsuarioPorId(UUID id);

    //Usuarios buscarUsuarioPorNome(String nome);
    List <Usuarios> buscarTodosUsuarios();
}
