package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Usuarios, UUID> {
    Optional<Usuarios> findByEmail(String name);
}
