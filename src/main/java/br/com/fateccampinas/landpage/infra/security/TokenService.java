package br.com.fateccampinas.landpage.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.com.fateccampinas.landpage.domain.user.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {
    private final String secret = "test-secret-key";

    public String generateToken(UsersEntity user, boolean keepUserLoggedIn) 
            throws IllegalArgumentException, UnsupportedEncodingException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getEmail())
                .withArrayClaim("roles", user.getAuthorities()
                                                .stream()
                                                .map(role -> role.getAuthority())
                                                .toArray(String[]::new)
                )
                .withExpiresAt(genExpirationDate(keepUserLoggedIn))
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token)
            throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Date genExpirationDate(boolean keepUserLoggedIn) {
        int howManyDays = (!keepUserLoggedIn) ? 14 : 365;
        return Date.from(LocalDateTime.now().plusDays(howManyDays)
                .atZone(ZoneId.of("-03:00"))
                .toInstant());
    }
}
