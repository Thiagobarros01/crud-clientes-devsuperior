package com.thiagosbarros.crudcliente.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thiagosbarros.crudcliente.config.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    private static final String SECRECT_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P"; // Chave secreta utilizada para gerar e verificar o token
    private static final String ISSUER = "pizzurg-api"; //EMISSOR DO TOKEN

    public String generateToken(UserDetailsImpl user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRECT_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())// Define o assunto do token (neste caso, o nome de usuário)
                    .sign(algorithm); // Assina o token usando o algoritmo especificado
        }
        catch (JWTCreationException exception){
        throw new JWTCreationException("Erro ao gerar token", exception);
        }

    }
    public String getSubjectFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRECT_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER) // Define o emissor do token
                    .build()
                    .verify(token) //Verifica a validade do token
                    .getSubject();// Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTCreationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }
    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();

    }
}



