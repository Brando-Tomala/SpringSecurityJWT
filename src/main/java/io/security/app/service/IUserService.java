package io.security.app.service;

import io.security.app.entitys.Rol;
import io.security.app.entitys.Usuario;

import java.util.List;

/**
 * @author Brando Tomala
 * @version 1.0
 * @since 01/10/2021
 */
public interface IUserService {
    Usuario saveUser(Usuario user);
    Rol saveRol(Rol rol);
    void addRolToUser(String username, String rolName);
    Usuario getUser(String username);
    List<Usuario> getUsers();
}
