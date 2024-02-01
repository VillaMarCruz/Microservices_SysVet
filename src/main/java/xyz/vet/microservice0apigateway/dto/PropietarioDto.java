package xyz.vet.microservice0apigateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PropietarioDto {

    private Long id;

    @NotNull
    @NotBlank(message = "El campo cédula no debe ser vacío.")
    @Size(min = 10, max = 10)
    private String cedula;

    @NotBlank(message = "El campo nombre no debe estar vacío.")
    @NotNull
    private String nombre;

    @NotBlank(message = "El campo apellido no debe estar vacío.")
    @NotNull
    private String apellido;

    private String picture;

    private String pais;

    private String provincia;

    private String ciudad;

    private String direccion;

    private String telefono;

    private Long idUser;

    @NotBlank(message = "El campo email no debe ser vacío.")
    @NotNull
    @Email(message = "Ingrese un correo electrónico válido.")
    private String email;

    private MascotaDto mascota;

}
