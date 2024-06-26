package com.examen.api.servicios;

import org.springframework.stereotype.Service;

import com.examen.api.dtos.UsuarioDTO;
import com.examen.api.entidades.Usuario;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;



/**
 * Servicio que gestiona la autenticación de usuarios en la aplicación.
 */
@Service
public interface UsuarioService {
	
    /**
     * Obtiene el servicio de detalles de usuario.
     *
     */
    UserDetailsService userDetailsService();

    /**
     * Obtiene una lista de todos los usuarios como objetos DTO.
     *
     * @return Lista de objetos UsuarioDTO.
     */
    List<UsuarioDTO> obtenerTodos();

    
    Usuario agregarUsuario(Usuario usuario);
    Page<Usuario> getAllUsuario(Pageable pageable);
    Usuario getUsuarioById(Long id);
    Usuario saveUsuario(Usuario venta);
    void deleteUsuario(Long id);
    Usuario updateUsuario(Long id, Usuario usuario);
    Page<Usuario> filtrarUsuario(String fecha, Pageable pageable);

 

}
