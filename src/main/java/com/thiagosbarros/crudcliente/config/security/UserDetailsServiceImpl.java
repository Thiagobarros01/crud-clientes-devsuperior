package com.thiagosbarros.crudcliente.config.security;

import com.thiagosbarros.crudcliente.entities.User;
import com.thiagosbarros.crudcliente.exception.ResourceNotFoundException;
import com.thiagosbarros.crudcliente.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(username)
               .orElseThrow(()->  new ResourceNotFoundException("Usuário não encontrado", username));
       return new UserDetailsImpl(user);
    }
}
