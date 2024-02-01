package xyz.vet.microservice0apigateway.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String mailTo;
    private String subject;
    private PropietarioDto propietario;
    private MascotaDto mascota;
    private CitaDto cita;

}
