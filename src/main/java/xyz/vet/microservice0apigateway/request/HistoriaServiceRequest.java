package xyz.vet.microservice0apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "historia-clinica-service",
        path = "/api/",
        url = "${historia-clinica.service.url}",
        configuration = FeignConfiguration.class
)
public interface HistoriaServiceRequest {

    /*
    * DESPARACITACION
    */

    @GetMapping("desparacitacion/lista")
    List<Object> getAllDesparacitaciones();

    @GetMapping("desparacitacion/lista/{idMascota}")
    List<Object> getAllDesparacitacionesByMascota(@PathVariable("idMascota") Long id);

    @GetMapping("desparacitacion/detalle/{id}")
    Object getDesparacitacionById(@PathVariable("id") Long id);

    @PostMapping("desparacitacion/create")
    Object saveDesparacitacion(@RequestBody Object desparacitacion);

    @PutMapping("desparacitacion/update/{id}")
    Object updateDesparacitacion(@PathVariable("id") Long id, @RequestBody Object desparacitacion);

    @DeleteMapping("desparacitacion/delete/{id}")
    void deleteDesparacitacion(@PathVariable("id") Long id);


    /*
     * VACUNACION
     */

    @GetMapping("vacunacion/lista")
    List<Object> getAllVacunaciones();

    @GetMapping("vacunacion/lista/{idMascota}")
    List<Object> getAllVacunacionesByMascota(@PathVariable("idMascota") Long id);

    @GetMapping("vacunacion/detalle/{id}")
    Object getVacunacionById(@PathVariable("id") Long id);

    @PostMapping("vacunacion/create")
    Object saveVacunacion(@RequestBody Object vacunacion);

    @PostMapping("vacunacion/exportCarnet")
    byte[] exportCarnet(@RequestBody Object dataVacunacion);

    @PutMapping("vacunacion/update/{id}")
    Object updateVacunacion(@PathVariable("id") Long id, @RequestBody Object vacunacion);

    @DeleteMapping("vacunacion/delete/{id}")
    void deleteVacunacion(@PathVariable("id") Long id);


    /*
     * HISTORIAS
     */

    @GetMapping("historia/lista")
    List<Object> getAllHistorias();

    @GetMapping("historia/lista/{idMascota}")
    List<Object> getAllHistoriasByMascota(@PathVariable("idMascota") Long id);

    @GetMapping("historia/detalle/{id}")
    Object getHistoriaById(@PathVariable("id") Long id);

    @PostMapping("historia/create")
    Object saveHistoria(@RequestBody Object historia);

    @PutMapping("historia/update/{id}")
    Object updateHistoria(@PathVariable("id") Long id, @RequestBody Object historia);

    @DeleteMapping("historia/delete/{id}")
    void deleteHistoria(@PathVariable("id") Long id);

    /*
     * EXAMENES OR EVIDENCIAS
     */

    @GetMapping("examen/lista")
    List<Object> getAllExamenes();

    @GetMapping("examen/lista")
    List<Object> getAllExamenesByHistoria(@RequestParam("historia") Long historia);

    @GetMapping("examen/lista")
    List<Object> getAllExamenesByMascota(@RequestParam("mascota") Long mascota);

    @GetMapping("examen/detalle/{id}")
    Object getExamenById(@PathVariable("id") Long id);

    @PostMapping("examen/create")
    Object saveExamen(@RequestBody Object examen);

    @PutMapping("examen/update/{id}")
    Object updateExamen(@PathVariable("id") Long id, @RequestBody Object examen);

    @DeleteMapping("examen/delete/{id}")
    void deleteExamen(@PathVariable("id") Long id);


}
