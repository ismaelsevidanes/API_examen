package com.examen.api.servicios;

import org.springframework.security.core.userdetails.UserDetails;


/**
 * Servicio que gestiona la autenticación de usuarios en la aplicación.
 */
public interface JwtService {
	String extractUserName(String token);

	String generateToken(UserDetails userDetails);

	boolean isTokenValid(String token, UserDetails userDetails);
	
	
	
	 
	
}
