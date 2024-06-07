package com.examen.api.configuracion.jwt;

import org.springframework.beans.factory.annotation.Autowired;




import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.examen.api.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

import com.examen.api.entidades.Role;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	@Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	UsuarioService usuarioService;
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1//**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.GET, "/api/v1/campos/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/campos/{id}").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/campos").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/campos/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/campos/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.GET, "/api/v1/reservas/**").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/reservas/{id}").hasAuthority(Role.ROLE_USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/reservas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/reservas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/reservas/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpFirewall looseHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//	@Autowired
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//	@Autowired
//     UsuarioService userService;
//    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request ->           
//                request
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/v1/libros/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
// 	           	.requestMatchers(HttpMethod.POST, "/api/v1/libros/**").hasAuthority(Role.ROLE_ADMIN.toString())
// 	           .requestMatchers(HttpMethod.PUT, "/api/v1/libros/**").hasAuthority(Role.ROLE_ADMIN.toString())
// 	          .requestMatchers(HttpMethod.DELETE, "/api/v1/libros/**").hasAuthority(Role.ROLE_ADMIN.toString())
// 	           	.requestMatchers("/api/v1/users/**").hasAuthority("ROLE_ADMIN")  // Modificado aquí
//                .anyRequest().authenticated())
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider()).addFilterBefore(
//                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService.userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
//            throws Exception {
//        return config.getAuthenticationManager();
//    }
//}




