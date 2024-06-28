package br.com.fateccampinas.landpage.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpDTO(
    @Email
    @NotBlank
    @Size(max = 50)
    String email,
    
    @NotBlank
    @Size(max = 80)
    String name,
    
    UserRole role,

    @NotBlank
    @Size(max = 60)
    String password,
    
    @Pattern(regexp = "\\d{11}")
    @NotBlank
    @Size(max = 20)
    String phoneNumber
) {
}