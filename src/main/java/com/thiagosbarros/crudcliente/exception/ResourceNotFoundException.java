package com.thiagosbarros.crudcliente.exception;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource.toUpperCase() + "_NOT_FOUND",
                resource + " com id=" + id + " não encontrado");
    }
}
