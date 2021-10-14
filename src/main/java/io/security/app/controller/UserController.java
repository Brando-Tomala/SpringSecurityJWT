package io.security.app.controller;

import io.security.app.entitys.Rol;
import io.security.app.entitys.Usuario;
import io.security.app.model.RoleToUserForm;
import io.security.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author Brando Tomala
 * @version 1.0
 * @since 02/10/2021
 */

@RestController
public class UserController {
    @Autowired
    IUserService userServ;

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getUsers() {
        return ResponseEntity.ok(userServ.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
        return ResponseEntity.status(CREATED).body(userServ.saveUser(user));
    }

    @PostMapping("/rol/save")
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol) {
        return ResponseEntity.status(CREATED).body(userServ.saveRol(rol));
    }

    @PostMapping("/rol/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUser) {
        userServ.addRolToUser(roleToUser.getUsername(), roleToUser.getRolName());
        return ResponseEntity.ok().build();
    }

}
