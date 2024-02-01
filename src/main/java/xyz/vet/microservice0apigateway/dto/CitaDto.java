package xyz.vet.microservice0apigateway.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CitaDto {
    private Long id;

    private Date fechaCita;

    private String duracion;

    private String motivo;

    private String doctor;

    private Long idCliente;

    private Long idMascota;

    private String estadoCita;

}
