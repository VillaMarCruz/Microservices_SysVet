package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.InventarioServiceRequest;

@RestController
@RequestMapping("/gateway/categoria-producto")
@CrossOrigin(origins = "**")
public class CategoriaController {

    @Autowired
    InventarioServiceRequest inventarioServiceRequest;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(inventarioServiceRequest.getAllCategorias());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(inventarioServiceRequest.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody Object category){
        return new ResponseEntity<>(inventarioServiceRequest.saveCategoria(category), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody Object category){
        return new ResponseEntity<>(inventarioServiceRequest.updateCategoria(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        inventarioServiceRequest.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
