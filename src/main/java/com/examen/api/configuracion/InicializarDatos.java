package com.examen.api.configuracion;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.examen.api.entidades.Role;
import com.examen.api.entidades.Usuario;
import com.examen.api.repositorios.UsuarioRepositorio;

/**
 * Clase que inicializa los datos de la aplicación.
 */
@Component

public class InicializarDatos implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	//@Autowired
    //private AuthenticationService authenticationService;
	
	 
	@Override
    public void run(String... args) throws Exception {
		
		Usuario usuarioAdmin =null;
		Usuario usuarioUser =null;
		Usuario usuario =null;
		
		try {
		// Usuario 1
        if (usuarioRepositorio.findByEmail("ismael.sevidanes@example.com").isEmpty()) {
        usuarioAdmin = new Usuario();
        usuarioAdmin.setNombre("Ismael");
        usuarioAdmin.setApellidos("Sevidanes");
        usuarioAdmin.setEmail("ismael.sevidanes@example.com");
        usuarioAdmin.setPassword("admin");
        usuarioAdmin.getRoles().add(Role.ROLE_ADMIN);
        usuarioAdmin.getRoles().add(Role.ROLE_USER);
        usuarioRepositorio.save(usuarioAdmin);
	 }else {
         System.out.println("El usuario ya existe");
     }
        
    		// Usuario 2
        if (usuarioRepositorio.findByEmail("manuel.rodriguez@example.com").isEmpty()) {
        usuarioUser = new Usuario();
        usuarioUser.setNombre("Mamuel");
        usuarioUser.setApellidos("Rodriguez");
        usuarioUser.setEmail("manuel.rodriguez@example.com");
        usuarioUser.setPassword("user");
        usuarioUser.getRoles().add(Role.ROLE_USER);
        usuarioRepositorio.save(usuarioUser);
            }else {
                System.out.println("El usuario ya existe");
            }
        
        if (usuarioRepositorio.findByEmail("usuario@example.com").isEmpty()) {
        usuario = new Usuario();
        usuario.setNombre("usuario");
        usuario.setApellidos("usuario");
        usuario.setEmail("usuario@example.com");
        //
        usuario.setPassword("usuario");
        usuario.getRoles().add(Role.ROLE_USER);
        usuarioRepositorio.save(usuario);
        }else {
            System.out.println("El usuario ya existe");
        }
        
        
        
//        authenticationService.signup(usuarioAdmin);
//        authenticationService.signup(usuarioUser);
//        authenticationService.signup(usuario);

 
        
        
        }catch(Exception e) {
            System.out.println("Excepción capturada: " + e.getMessage());
    	}
	}
		
	
}
