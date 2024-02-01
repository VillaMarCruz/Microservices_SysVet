package xyz.vet.microservice0apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.MascotaDto;
import xyz.vet.microservice0apigateway.dto.PropietarioDto;

import java.util.List;

@FeignClient(
        name = "propietario-mascota-service",
        path = "/api/",
        url = "${mascota-propietario.service.url}",
        configuration = FeignConfiguration.class
)
public interface PropietarioMascotaServiceRequest {

    @PostMapping("propietario/create")
    Object savePropietario(@RequestBody Object requestBody);

    @DeleteMapping("propietario/delete/{propietarioId}")
    void deletePropietario(@PathVariable("propietarioId") Long propietarioId);

    @PutMapping("propietario/update/{propietarioId}")
    Object updatePropietario(@PathVariable("propietarioId") Long propietarioId, @RequestBody Object requestBody);

    @GetMapping("propietario/list")
    List<Object> getAllPropietarios();

    @GetMapping("propietario/detail/{id}")
    PropietarioDto getPropietarioById(@PathVariable("id") Long id);

    @GetMapping("propietario/detailByIdUser/{id}")
    Object getPropietarioByIdUser(@PathVariable("id") Long id);

    //
    @GetMapping("mascota/list")
    List<Object> getAllMascotas();

    @GetMapping("mascota/list/{propietarioId}")
    List<Object> getAllMascotasOfPropietarios(@PathVariable("propietarioId") Long propietarioId);

    @GetMapping("mascota/listByCedulaPropietario/{cedula}")
    List<Object> getAllMascotasOfCedulaPropietario(@PathVariable("cedula") String cedula);

    @GetMapping("mascota/detalle/{idMascota}")
    MascotaDto getMascotaById(@PathVariable("idMascota") Long idMascota);

    @PostMapping("mascota/create")
    Object saveMascota(@RequestBody Object requestBody);

    @DeleteMapping("mascota/delete/{mascotaId}")
    void deleteMascota(@PathVariable("mascotaId") Long mascotaId);

    @PutMapping("mascota/update/{mascotaId}")
    Object updateMascota(@PathVariable("mascotaId") Long mascotaId, @RequestBody Object requestBody);



}
