package xyz.vet.microservice0apigateway.dto;

import lombok.Data;

@Data
public class Mensaje {

    private String mensaje;

    private Object data;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje(String mensaje, Object data) {
        this.mensaje = mensaje;
        this.data = data;
    }

    public Mensaje() {

    }
}
