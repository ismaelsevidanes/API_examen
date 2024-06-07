package com.examen.api.controladores;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.examen.api.controlador.UsuariosControlador;
import com.examen.api.entidades.Usuario;
import com.examen.api.servicios.UsuarioService;


@WebMvcTest(UsuariosControlador.class)
class UsuarioControladorTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private UsuarioService usuarioServicio;
	    
	 // Crear una lista ficticia de estudiantes
        List<Usuario> usuariosMock = new ArrayList<>();
	    
	    @BeforeEach
	    public void setUp() {

	        Usuario usuario1= new Usuario();
	        usuario1.setId(1L);
	        usuario1.setNombre("Usuario 1");
	        usuariosMock.add(usuario1);

	        Usuario usuario2= new Usuario();
	        usuario1.setId(1L);
	        usuario1.setNombre("Usuario 1");
	        usuariosMock.add(usuario1);
	        
	  	    }

}
