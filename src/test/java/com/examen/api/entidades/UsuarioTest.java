package com.examen.api.entidades;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


/**
 * Clase de prueba unitaria para la clase Usuario.
 */

public class UsuarioTest {

	private static Validator validator;

	/**
     * Configura el validador antes de ejecutar las pruebas.
     */
	@BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

/**
     * Prueba para un objeto Usuario válido.
     */
    @Test
    void testUsuario_Valid() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Ismael");
        usuario.setApellidos("Sevidanes");
        usuario.setEmail("ismael.sevidanes@example.com");
        usuario.setPassword("root");
        
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty());
    }
    
    
	/**
	 * Prueba para un objeto Usuario con nombre no válido.
	 */
    @Test
    void testUsuario_InvalidNombre() {
        	 Usuario usuario = new Usuario();
             usuario.setNombre("");
             usuario.setApellidos("Sevidanes");
             usuario.setEmail("ismael.sevidanes@example.com");
             usuario.setPassword("root");
             
             Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

             assertEquals(1, violations.size());
             ConstraintViolation<Usuario> violation = violations.iterator().next();
             assertEquals("El nombre no puede estar en blanco", violation.getMessage());
         }
    
        /**
		 * Prueba para un objeto Usuario con apellidos no válidos.
		 */
         @Test
         void testUsuario_InvalidApellido() {
             	 Usuario usuario = new Usuario();
                  usuario.setNombre("Ismael");
                  usuario.setApellidos("");
                  usuario.setEmail("ismael.sevidanes@example.com");
                  usuario.setPassword("root");
                  
                  Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                  assertEquals(1, violations.size());
                  ConstraintViolation<Usuario> violation = violations.iterator().next();
                  assertEquals("El apellido no puede estar en blanco", violation.getMessage());
              }
         
       
            /**
       		 * Prueba para un objeto Usuario con email no válidos.
       		 */
                @Test
                void testUsuario_InvalidEmail() {
                    	 Usuario usuario = new Usuario();
                         usuario.setNombre("Ismael");
                         usuario.setApellidos("Sevidanes");
                         usuario.setEmail("");
                         usuario.setPassword("root");
                         
                         Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                         assertEquals(1, violations.size());
                         ConstraintViolation<Usuario> violation = violations.iterator().next();
                         assertEquals("El email no puede estar en blanco", violation.getMessage());
                     }
                /**
           		 * Prueba para un objeto Usuario con contraseña no válidos.
           		 */
                    @Test
                    void testUsuario_InvalidPassword() {
                        	 Usuario usuario = new Usuario();
                             usuario.setNombre("Ismael");
                             usuario.setApellidos("Sevidanes");
                             usuario.setEmail("ismael.sevidanes@example.com");
                             usuario.setPassword("");
                             
                             Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                             assertEquals(1, violations.size());
                             ConstraintViolation<Usuario> violation = violations.iterator().next();
                             assertEquals("La contraseña no puede estar en blanco", violation.getMessage());
                         }
                    
							/**
							 * Prueba para el método getAuthorities.
							 */
                    @Test
                    void testUsuarioAuthorities() {
                        Usuario usuario = new Usuario();
                        usuario.setId(1L);
                        usuario.setNombre("Ismael");
                        usuario.setApellidos("Sevidanes");
                        usuario.setEmail("ismael.sevidanes@example.com");
                        usuario.setPassword("root");
                        usuario.setRoles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));

                        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

                        assertThat(authorities)
                            .hasSize(2)
                            .extracting(GrantedAuthority::getAuthority)
                            .containsExactlyInAnyOrder(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name());
                    }
                    
                    
                    /**
                     * Prueba para los métodos getters y setters de Usuario.
                     */
                    @Test
                    void testUsuarioGettersAndSetters() {
                        Usuario usuario = new Usuario();
                        usuario.setId(1L);
                        usuario.setNombre("Ismael");
                        usuario.setApellidos("Sevidanes");
                        usuario.setEmail("ismael.sevidanes@example.com");
                        usuario.setPassword("root");

                        assertThat(usuario.getId()).isEqualTo(1L);
                        assertThat(usuario.getNombre()).isEqualTo("Ismael");
                        assertThat(usuario.getApellidos()).isEqualTo("Sevidanes");
                        assertThat(usuario.getEmail()).isEqualTo("ismael.sevidanes@example.com");
                        assertThat(usuario.getPassword()).isEqualTo("root");
                    }

					/**
					 * Prueba para el método builder de Usuario.
					 */
                    @Test
                    void testUsuarioBuilder() {
                        Usuario usuario= Usuario.builder()
                            .id(1L)
                            .nombre("Ismael")
                            .apellidos("Sevidanes")
                            .email("ismael.sevidanes@example.com")
                            .password("root")
                            .roles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN))
                            .build();

                        assertThat(usuario.getId()).isEqualTo(1L);
                        assertThat(usuario.getNombre()).isEqualTo("Ismael");
                        assertThat(usuario.getApellidos()).isEqualTo("Sevidanes");
                        assertThat(usuario.getEmail()).isEqualTo("ismael.sevidanes@example.com");
                        assertThat(usuario.getPassword()).isEqualTo("root");
                        assertThat(usuario.getRoles()).containsExactlyInAnyOrder(Role.ROLE_USER, Role.ROLE_ADMIN);
                    }
    
    }
        