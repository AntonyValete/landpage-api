package br.com.fateccampinas.landpage.domain.user;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @Email
    @NotBlank
    String email,

    @NotBlank
    String password,

    @DefaultValue("false")
    boolean rememberMe
) {
}
