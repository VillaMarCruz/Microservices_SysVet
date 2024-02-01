package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.Mensaje;
import xyz.vet.microservice0apigateway.request.HistoriaServiceRequest;

@RestController
@RequestMapping("/gateway/historia")
@CrossOrigin(origins = "**")
public class HistoriaController {
    @Autowired
    private HistoriaServiceRequest historiaServiceRequest;

    @GetMapping("lista")
    public ResponseEntity<?> getAllHistorias(){
        return ResponseEntity.ok(historiaServiceRequest.getAllHistorias());
    }

    @GetMapping("lista/{idMascota}")
    public ResponseEntity<?> getAllHistoriasByMascota(@PathVariable("idMascota") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getAllHistoriasByMascota(id));
    }

    @GetMapping("detalle/{id}")
    public ResponseEntity<?> getHistoriaById(@PathVariable("id") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getHistoriaById(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Object historia){
        return new ResponseEntity<>(historiaServiceRequest.saveHistoria(historia), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Object historia, @PathVariable("id") Long id){
        return new ResponseEntity<>(historiaServiceRequest.updateHistoria(id, historia), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        historiaServiceRequest.deleteHistoria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
