package com.thiagosbarros.crudcliente.dto;

import com.thiagosbarros.crudcliente.entities.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
