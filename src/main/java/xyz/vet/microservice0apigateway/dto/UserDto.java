package xyz.vet.microservice0apigateway.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.vet.microservice0apigateway.model.Role;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "El campo username no debe ser vacío.")
    @NotNull
    private String username;

    @NotBlank(message = "El campo password no debe ser vacío.")
    @NotNull
    private String password;

    @NotBlank(message = "El campo nombre no debe ser vacío.")
    @NotNull
    private String nombre;

    @NotBlank(message = "El campo apellido no debe ser vacío.")
    @NotNull
    private String apellido;

    @NotBlank(message = "El campo email no debe ser vacío.")
    @NotNull
    @Email(message = "Ingrese un correo electrónico válido.")
    private String email;

    private PropietarioDto propietario;

}
