package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.MascotaDto;
import xyz.vet.microservice0apigateway.dto.PropietarioDto;
import xyz.vet.microservice0apigateway.request.HistoriaServiceRequest;
import xyz.vet.microservice0apigateway.request.PropietarioMascotaServiceRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/gateway/vacunacion")
@CrossOrigin(origins = "**")
public class VacunacionController {
    @Autowired
    private HistoriaServiceRequest historiaServiceRequest;

    @Autowired
    private PropietarioMascotaServiceRequest propietarioMascotaServiceRequest;

    @GetMapping("lista")
    public ResponseEntity<?> getAllVacunaciones(){
        return ResponseEntity.ok(historiaServiceRequest.getAllVacunaciones());
    }

    @GetMapping("lista/{idMascota}")
    public ResponseEntity<?> getAllVacunacionesByMascota(@PathVariable("idMascota") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getAllVacunacionesByMascota(id));
    }

    @GetMapping("detalle/{id}")
    public ResponseEntity<?> getVacunacionById(@PathVariable("id") Long id){
        return ResponseEntity.ok(historiaServiceRequest.getVacunacionById(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Object vacunacion){
        return new ResponseEntity<>(historiaServiceRequest.saveVacunacion(vacunacion), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Object vacunacion, @PathVariable("id") Long id){
        return new ResponseEntity<>(historiaServiceRequest.updateVacunacion(id, vacunacion), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        historiaServiceRequest.deleteVacunacion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //EXPORTAR PDF CARNET VACUNACION
    @GetMapping("carnetVacunacion/{mascotaId}")
    public ResponseEntity<?> exportCarnet(@PathVariable("mascotaId") Long mascotaId){
        MascotaDto mascota = propietarioMascotaServiceRequest.getMascotaById(mascotaId);

        PropietarioDto propietario = propietarioMascotaServiceRequest.getPropietarioById(mascota.getPropietarioId());

        propietario.setMascota(mascota);

        byte[] reporte = historiaServiceRequest.exportCarnet(propietario);

        // Crear un objeto ByteArrayResource con el reporte
        ByteArrayResource resource = new ByteArrayResource(reporte);

        String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
        StringBuilder stringBuilder = new StringBuilder().append("CartillaPDF:");
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(stringBuilder.append("Cartilla")
                        .append(propietario.getMascota().getNombre())
                        .append(sdf)
                        .append(".pdf")
                        .toString())
                .build();

        // Configurar las cabeceras de la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(contentDisposition);

        // Devolver el reporte como respuesta HTTP
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(reporte.length)
                .body(resource);
    }

}
