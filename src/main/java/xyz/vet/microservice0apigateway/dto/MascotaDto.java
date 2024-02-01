package xyz.vet.microservice0apigateway.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class MascotaDto {
    private Long id;

    private String nombre;

    private String picture;

    private Date fechaNacimiento;

    private String especie;

    private String raza;

    private String sexo;

    private String descripcion;

    private Long propietarioId;

    private String cedulaPropietario;

}
