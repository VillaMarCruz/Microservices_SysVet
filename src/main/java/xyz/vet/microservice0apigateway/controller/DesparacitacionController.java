package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.HistoriaServiceRequest;

@RestController
@RequestMapping("/gateway/desparacitacion")
@CrossOrigin(origins = "**")
public class DesparacitacionController {

    @Autowired
    private HistoriaServiceRequest historiaServiceRequest;

    @GetMapping("lista")
    public ResponseEntity<?> getAllDesparacitaciones(){
        return ResponseEntity.ok(historiaServiceRequest.getAllDesparacitaciones());
    }

    @GetMapping("lista/{idMascota}")
    public ResponseEntity<?> getAllDesparacitacionesByMascota(@PathVariable("idMascota") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getAllDesparacitacionesByMascota(id));
    }

    @GetMapping("detalle/{id}")
    public ResponseEntity<?> getDesparacitacionById(@PathVariable("id") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getDesparacitacionById(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Object desparacitacion){
        return new ResponseEntity<>(historiaServiceRequest.saveDesparacitacion(desparacitacion), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Object desparacitacion, @PathVariable("id") Long id){
        return new ResponseEntity<>(historiaServiceRequest.updateDesparacitacion(id, desparacitacion), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        historiaServiceRequest.deleteDesparacitacion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
