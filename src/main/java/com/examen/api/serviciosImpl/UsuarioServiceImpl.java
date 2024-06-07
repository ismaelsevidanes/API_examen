package com.examen.api.serviciosImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.examen.api.repositorios.UsuarioRepositorio;
import com.examen.api.dtos.UsuarioDTO;
import com.examen.api.entidades.Usuario;
import com.examen.api.error.exception.UsuarioNotFoundException;
import com.examen.api.servicios.UsuarioService;

import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl  implements UsuarioService {
	

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDTO> obtenerTodos() {
		return null;
	}

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Page<Usuario> getAllUsuario(Pageable pageable) {
		 return usuarioRepositorio.findAll(pageable);
	}

	@Override
	public Usuario getUsuarioById(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);

	}

	@Override
	public void deleteUsuario(Long id) {
		usuarioRepositorio.deleteById(id);
		
	}

	@Override
	public Usuario updateUsuario(Long id, @Valid Usuario detallesUsuario) {
	    Usuario usuario = getUsuarioById(id);
	    usuario.setNombre(detallesUsuario.getNombre());
	    usuario.setApellidos(detallesUsuario.getApellidos());
	    usuario.setEmail(detallesUsuario.getEmail());
	    usuario.setPassword(detallesUsuario.getPassword());

        // Actualiza otros campos necesarios
        return usuarioRepositorio.save(usuario);
	}

	@Override
	public Page<Usuario> filtrarUsuario(String fecha, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
