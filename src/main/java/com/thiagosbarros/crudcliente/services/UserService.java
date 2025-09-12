package com.thiagosbarros.crudcliente.services;

import com.thiagosbarros.crudcliente.config.jwt.JwtTokenService;
import com.thiagosbarros.crudcliente.config.security.SecurityConfiguration;
import com.thiagosbarros.crudcliente.config.security.UserDetailsImpl;
import com.thiagosbarros.crudcliente.dto.CreateUserDto;
import com.thiagosbarros.crudcliente.dto.LoginUserDto;
import com.thiagosbarros.crudcliente.dto.RecoveryJwtTokenDto;
import com.thiagosbarros.crudcliente.entities.Role;
import com.thiagosbarros.crudcliente.entities.User;
import com.thiagosbarros.crudcliente.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserRepository userRepository;
    private SecurityConfiguration securityConfiguration;

    public UserService(AuthenticationManager authenticationManager,
                       JwtTokenService jwtTokenService,
                       UserRepository userRepository,
                       SecurityConfiguration securityConfiguration) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.securityConfiguration = securityConfiguration;
    }

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        Role role = new Role();
        role.setName(createUserDto.role());
        // Cria um novo usuário com os dados fornecidos
        User newUser = new User();
        newUser.setEmail(createUserDto.email());
        // Codifica a senha do usuário com o algoritmo bcrypt
        newUser.setPassword(securityConfiguration.passwordEncoder().encode(createUserDto.password()));
        // Atribui ao usuário uma permissão específica
        newUser.setRoles(List.of(role));

        userRepository.save(newUser);

    }

}
