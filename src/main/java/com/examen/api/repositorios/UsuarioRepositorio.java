package com.examen.api.repositorios;


import java.util.Optional;



import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.examen.api.entidades.Usuario;

/**
 * Repositorio de datos para la entidad Usuario. Proporciona métodos para
 * realizar operaciones CRUD sobre la base de datos.
 */
	public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
		/**
		 * Busca un usuario por su nombre de usuario.
		 *
		 * @param username El nombre de usuario del usuario a buscar.
		 * @return El usuario con el nombre de usuario especificado, o null si no se
		 *         encuentra.
		 */
		 Optional<Usuario> findById(Long id);
		 Optional<Usuario> findByEmail(String email);

		
		/**
		 * Comprueba si un usuario con el nombre de usuario especificado existe en la
		 * base de datos.
		 */
		Boolean existsById(Long id);
		Boolean existsByEmail(String email);

		
    
    /**
     * Busca un usuario por su nombre de usuario o dirección de correo electrónico.
     */
    
	 Optional<Usuario> deleteById(Long id);

    Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
	
    
}
