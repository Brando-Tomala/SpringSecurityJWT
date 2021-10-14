package io.security.app.service;

import io.security.app.dao.RolDAO;
import io.security.app.dao.UsuarioDAO;
import io.security.app.entitys.Rol;
import io.security.app.entitys.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Brando Tomala
 * @version 1.0
 * @since 01/10/2021
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    UsuarioDAO userDAO;

    @Autowired
    RolDAO rolDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userDAO.findByUsername(username);
        if (user == null) {
            log.error("Usuario {} no encontrado en base de datos", username);
            throw new UsernameNotFoundException("Usuario no encontrado en base de datos");
        } else
            log.info("Usuario encontrado en base de datos: {}", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(
                rol -> {
                    authorities.add(new SimpleGrantedAuthority(rol.getName()));
                }
        );

        return new User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public Usuario saveUser(Usuario user) {
        log.info("Guardando usuario {} en base de datos", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public Rol saveRol(Rol rol) {
        log.info("Guardando rol {} en base de datos", rol.getName());
        return rolDAO.save(rol);
    }

    @Override
    public void addRolToUser(String username, String rolName) {
        log.info("Agregando rol {} a usuario {}", username, rolName);
        Usuario user = userDAO.findByUsername(username);
        Rol rol = rolDAO.findByName(rolName);
        user.getRoles().add(rol);
    }

    @Override
    public Usuario getUser(String username) {
        log.info("Obtener usuario {}", username);
        return userDAO.findByUsername(username);
    }

    @Override
    public List<Usuario> getUsers() {
        log.info("Obtener todos los usuarios");
        return userDAO.findAll();
    }


}
