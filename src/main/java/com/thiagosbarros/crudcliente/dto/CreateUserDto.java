package com.thiagosbarros.crudcliente.dto;

import com.thiagosbarros.crudcliente.enums.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {
}
