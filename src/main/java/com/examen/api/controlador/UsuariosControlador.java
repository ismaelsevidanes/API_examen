package com.examen.api.controlador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examen.api.entidades.Usuario;
import com.examen.api.servicios.UsuarioService;



@RestController
@RequestMapping("/api/users")
public class UsuariosControlador {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuariosControlador.class);
	
	private UsuarioService usuarioService;
	
	// Endpoint para obtener un listado de usuario
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Usuario>> getAllUsuario(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        logger.info("UsuariosControlador :: getAllUsuario");
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> libros = usuarioService.getAllUsuario(pageable);
        
   
        
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
    
    // CRUD endpoints, accesibles solo por ROLE_ADMIN
    // Crear un nuevo usuario
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }
    
	 // Leer un usuario por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }
    
    // Actualizar un usuario
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario UsuarioDetails) {
        return usuarioService.updateUsuario(id, UsuarioDetails);
    }
    
    // Eliminar un libro
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUsuario(@PathVariable Long id) {
    	usuarioService.deleteUsuario(id);
    }
    
    
    

	
	

	
}
