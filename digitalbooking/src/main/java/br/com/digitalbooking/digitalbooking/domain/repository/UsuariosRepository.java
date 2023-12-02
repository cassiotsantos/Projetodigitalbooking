package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

    Optional<Usuarios> findUsuarioByEmail(String email);

    Optional<Usuarios> findByNomeIgnoreCaseOrEmailEquals(String nome, String email);

}
