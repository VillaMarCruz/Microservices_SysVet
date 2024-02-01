package xyz.vet.microservice0apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.CitaDto;
import xyz.vet.microservice0apigateway.dto.Mensaje;

import java.util.List;

@FeignClient(
        name = "agendamiento-service",
        path = "/api/",
        url = "${agendamiento.service.url}",
        configuration = FeignConfiguration.class
)
public interface AgendamientoServiceRequest {

    @PostMapping("cita/create")
    Mensaje saveCita(@RequestBody Object requestBody);

    @PutMapping("cita/update/{id}")
    Object updateCita(@PathVariable("id") Long id, @RequestBody Object requestBody);

    @DeleteMapping("cita/delete/{id}")
    void deleteCita(@PathVariable("id") Long id);

    @GetMapping("cita/lista")
    List<CitaDto> getAllCitas();

    @GetMapping("cita/lista/byIdMascota/{id}")
    List<Object> getAllCitasByIdMascota(@PathVariable("id") Long id);

    @GetMapping("cita/lista/byIdPropietario/{id}")
    List<Object> getAllCitasByIdPropietario(@PathVariable("id") Long id);

    @GetMapping("cita/detail/{id}")
    Object getCitaById(@PathVariable("id") Long id);

}
