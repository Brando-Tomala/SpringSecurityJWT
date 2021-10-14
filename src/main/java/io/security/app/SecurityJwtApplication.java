package io.security.app;

import io.security.app.entitys.Rol;
import io.security.app.entitys.Usuario;
import io.security.app.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserServiceImpl userService){
		return args -> {
			userService.saveRol(new Rol(null, "ROLE_USER"));
			userService.saveRol(new Rol(null, "ROLE_MANAGER"));
			userService.saveRol(new Rol(null, "ROLE_ADMIN"));
			userService.saveRol(new Rol(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new Usuario(null, "Brando Tomala", "btomala",  "12345", new ArrayList<>()));
			userService.saveUser(new Usuario(null, "Will Smith", "wsmith", "12345", new ArrayList<>()));
			userService.saveUser(new Usuario(null, "Leo Dcaprio", "ldcaprio", "12345", new ArrayList<>()));
			userService.saveUser(new Usuario(null, "Roronoa Zoro", "rzoro", "12345", new ArrayList<>()));

			userService.addRolToUser("btomala", "ROLE_SUPER_ADMIN");
			userService.addRolToUser("btomala", "ROLE_ADMIN");
			userService.addRolToUser("btomala", "ROLE_USER");
			userService.addRolToUser("wsmith", "ROLE_MANAGER");
			userService.addRolToUser("ldcaprio", "ROLE_ADMIN");
			userService.addRolToUser("ldcaprio", "ROLE_USER");
			userService.addRolToUser("rzoro", "ROLE_USER");
		};
	}

}
