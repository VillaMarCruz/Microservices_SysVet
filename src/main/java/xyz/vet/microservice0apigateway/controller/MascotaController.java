package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.PropietarioMascotaServiceRequest;

@RestController
@RequestMapping("/gateway/mascota")
@CrossOrigin(origins = "**")
public class MascotaController {

    @Autowired
    private PropietarioMascotaServiceRequest propietarioMascotaServiceRequest;

    @GetMapping("lista")
    public ResponseEntity<?> getAllMascotas(){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getAllMascotas());
    }

    @GetMapping("detalle/{idMascota}")
    public ResponseEntity<?> getMascotaById(@PathVariable("idMascota") Long id){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getMascotaById(id));
    }

    @GetMapping("byPropietario/{idPropietario}")
    public ResponseEntity<?> getAllMascotasOfPropietario(@PathVariable("idPropietario") Long idPropietario){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getAllMascotasOfPropietarios(idPropietario));
    }

    @GetMapping("byCedulaPropietario/{cedula}")
    public ResponseEntity<?> getAllMascotasOfCedulaPropietario(@PathVariable("cedula") String cedula){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getAllMascotasOfCedulaPropietario(cedula));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Object mascota){
        return new ResponseEntity<>(propietarioMascotaServiceRequest.saveMascota(mascota), HttpStatus.CREATED);
    }

    @PutMapping("{mascotaId}")
    public ResponseEntity<?> update(@PathVariable("mascotaId") Long mascotaId, @RequestBody Object mascota){
        return new ResponseEntity<>(propietarioMascotaServiceRequest.updateMascota(mascotaId, mascota), HttpStatus.OK);
    }

    @DeleteMapping("{mascotaId}")
    public ResponseEntity<?> delete(@PathVariable("mascotaId") Long mascotaId){
        propietarioMascotaServiceRequest.deleteMascota(mascotaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
