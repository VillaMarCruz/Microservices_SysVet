package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.Mensaje;
import xyz.vet.microservice0apigateway.request.HistoriaServiceRequest;

import java.util.Optional;

@RestController
@RequestMapping("/gateway/examen")
@CrossOrigin(origins = "**")
public class ExamenController {
    @Autowired
    private HistoriaServiceRequest examenServiceRequest;

    @GetMapping("lista/all")
    public ResponseEntity<?> getAllHistoriasPrueba(){
        return ResponseEntity.ok(examenServiceRequest.getAllExamenes());
    }

    @GetMapping("lista")
    public ResponseEntity<?> getAllHistorias(@RequestParam("mascota") Optional<Long> mascota, @RequestParam("historia") Optional<Long> historia, @RequestParam("propietario") Optional<Long> propietario){

        if(mascota.isPresent() || propietario.isPresent() || historia.isPresent()){
            if(mascota.isPresent())
                return ResponseEntity.ok(examenServiceRequest.getAllExamenesByMascota(mascota.get()));
                //return ResponseEntity.ok("Este es mascota" + mascota.get());
            if (historia.isPresent())
                return ResponseEntity.ok(examenServiceRequest.getAllExamenesByHistoria(historia.get()));
                //return ResponseEntity.ok("Este es historia" + historia.get());
        }else{
            return ResponseEntity.ok(examenServiceRequest.getAllExamenes());
        }
        return new ResponseEntity<>(new Mensaje("No hay examenes"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("detalle/{id}")
    public ResponseEntity<?> getHistoriaById(@PathVariable("id") Long id){
        return ResponseEntity.ok(examenServiceRequest.getExamenById(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Object examen){
        return new ResponseEntity<>(examenServiceRequest.saveExamen(examen), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Object examen, @PathVariable("id") Long id){
        return new ResponseEntity<>(examenServiceRequest.updateExamen(id, examen), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        examenServiceRequest.deleteExamen(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
