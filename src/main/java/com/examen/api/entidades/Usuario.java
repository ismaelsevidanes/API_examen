package com.examen.api.entidades;

import java.util.Collection;





import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.CollectionTable;
//imports
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
//import lombok.Getter;
//import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	  private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  

	  
	  @NotBlank(message = "El nombre no puede estar vacío") // Asegura que el nombre no sea null o esté vacío
	   @Size(min=1, max = 25, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud del nombre
	    private String nombre;
	  
	  @NotBlank(message = "Los apellidos no puede estar vacío") // Asegura que no sea null o esté vacío
	   @Size(min=1, max = 35, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud 
	    private String apellidos;
	    
	    
	    @NotBlank(message = "El email no puede estar vacío") // Asegura que no sea null o esté vacío
	    private String email;
	    
	  @NotBlank(message = "La contraseña no puede estar vacío") // Asegura que no sea null o esté vacío
	    private String password; 
	  
	  /**
	     * Rol del usuario
	     */
	    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
	    @Enumerated(EnumType.STRING)
	    @CollectionTable(name = "usuario_rol")
	    @Column(name = "Roles")
	    private Set<Role> roles = new HashSet<>();

	    /**
	     * Devuelve la colección de roles asignados al usuario.
	     *
	     * @return Colección de roles.
	     */
	    @Transactional
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        roles.size();
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
	    }
	    
	  	public Long getId() {
	  		return id;
	  	}

		public void setId(Long id) {
			this.id = id;
		}
	    

	  
		public String getNombre() {
			return nombre;
		}
	    
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    public String getApellidos() {
			return apellidos;
		}
	    
	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }
	    
	    
		public String getEmail() {
			return email;
		}
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
		public String getPassword() {
			return password;
		}
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
	    
		public void addRole(Role role) {
			roles.add(role);
		}
		

	
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}
		

		
		 public static UsuarioBuilder builder() {
		        return new UsuarioBuilder();
		    }
		    
		    public static class UsuarioBuilder {
		        private Long id;
		        private String nombre;
		        private String apellidos;
		        private String email;
		        private String password;
		        private Set<Role> roles = new HashSet<>();

		        private UsuarioBuilder() {
		        }

		        public UsuarioBuilder id(Long id) {
		            this.id = id;
		            return this;
		        }

		        public UsuarioBuilder nombre(String nombre) {
		            this.nombre = nombre;
		            return this;
		        }

		        public UsuarioBuilder apellidos(String apellidos) {
		            this.apellidos = apellidos;
		            return this;
		        }

		        public UsuarioBuilder email(String email) {
		            this.email = email;
		            return this;
		        }

		        public UsuarioBuilder password(String password) {
		            this.password = password;
		            return this;
		        }

		        public UsuarioBuilder roles(Set<Role> roles) {
		            this.roles = roles;
		            return this;
		        }

		        public Usuario build() {
		        	Usuario usuario = new Usuario();
		        	usuario.setId(id);
		        	usuario.setNombre(nombre);
		        	usuario.setApellidos(apellidos);
		        	usuario.setEmail(email);
		        	usuario.setPassword(password);
		        	usuario.setRoles(roles);
		            return usuario;
		        }
		    }
}
		