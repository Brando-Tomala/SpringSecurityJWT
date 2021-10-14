package io.security.app.dao;

import io.security.app.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Brando Tomala
 * @version 1.0
 * @since 01/10/2021
 */
@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}

