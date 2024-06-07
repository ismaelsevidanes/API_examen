package com.examen.api.dtos;

/**
 * DTO (Data Transfer Object) representa un usuario. Se utiliza para transferir
 * información de la aplicación.
 * Ejercicio 1 apartado b
 */

public class UsuarioDTO {

	private String nombre;
	private String email;
    private String rol;
	private String password;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String nombre, String email, String rol,String password) {
		this.nombre = nombre;
		this.email = email;
        this.rol = rol;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
        this.rol = rol;
    }

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UsuarioDTO [nombre=" + nombre + ", email=" + email + ", rol=" + rol + ", password=" + password + "]";
	}
	
	
	
	
}
