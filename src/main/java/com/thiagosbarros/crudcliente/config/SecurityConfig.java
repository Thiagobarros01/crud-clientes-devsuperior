package com.thiagosbarros.crudcliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        var user = User.withUsername("user")
                .password("password")
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Configuração de regra de acesso
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //CSRF PARA API REST É DESABILITADO
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuador/health").permitAll()
                        // GET pode ser feito por USER or ADMIN
                        .requestMatchers(HttpMethod.GET, "/clients/**").hasAnyAuthority("ADMIN", "USER")

                        // POST, DELETE or PUT poder ser feito por ADMIN
                        .requestMatchers(HttpMethod.POST, "/clients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clients/**").hasRole("ADMIN")
                        // qualquer outra requisição precisa estar autenticada
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/login").permitAll());
        return http.build();

    }

}
