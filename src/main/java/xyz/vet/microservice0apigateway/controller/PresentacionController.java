package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.InventarioServiceRequest;

@RestController
@RequestMapping("/gateway/presentacion-producto")
@CrossOrigin(origins = "**")
public class PresentacionController {

    @Autowired
    InventarioServiceRequest inventarioServiceRequest;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllPresentaciones(){
        return ResponseEntity.ok(inventarioServiceRequest.getAllPresentaciones());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getPresentacionById(@PathVariable("id") Long id){
        return ResponseEntity.ok(inventarioServiceRequest.getPresentacionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePresentacion(@RequestBody Object category){
        return new ResponseEntity<>(inventarioServiceRequest.savePresentacion(category), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePresentacion(@PathVariable("id") Long id, @RequestBody Object category){
        return new ResponseEntity<>(inventarioServiceRequest.updatePresentacion(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePresentacion(@PathVariable("id") Long id){
        inventarioServiceRequest.deletePresentacion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
