package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.vet.microservice0apigateway.dto.CitaDto;
import xyz.vet.microservice0apigateway.dto.EmailDto;
import xyz.vet.microservice0apigateway.dto.MascotaDto;
import xyz.vet.microservice0apigateway.dto.PropietarioDto;
import xyz.vet.microservice0apigateway.request.AgendamientoServiceRequest;
import xyz.vet.microservice0apigateway.request.EmailServiceRequest;
import xyz.vet.microservice0apigateway.request.PropietarioMascotaServiceRequest;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

@Component
@RestController
@RequestMapping("/gateway/email")
@CrossOrigin(origins = "**")
public class EmailController {

    @Autowired
    private EmailServiceRequest emailServiceRequest;

    @Autowired
    private AgendamientoServiceRequest agendamientoServiceRequest;

    @Autowired
    private PropietarioMascotaServiceRequest propietarioMascotaServiceRequest;

    @Scheduled(cron = "${cron.job}")
    public void enviarEmail(){
        List<CitaDto> citas = agendamientoServiceRequest.getAllCitas();

        for (int i = 0; i < citas.toArray().length; i++) {
            CitaDto cita = citas.get(i);
            Date fechaCita = cita.getFechaCita();
            Date fechaLocal = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if(fechaCita.after(fechaLocal)){
                MascotaDto mascota = propietarioMascotaServiceRequest.getMascotaById(cita.getIdMascota());
                PropietarioDto propietario = propietarioMascotaServiceRequest.getPropietarioById(cita.getIdCliente());

                EmailDto emailDto = new EmailDto();
                emailDto.setPropietario(propietario);
                emailDto.setMascota(mascota);
                emailDto.setCita(cita);

                emailServiceRequest.createEmail(emailDto);
                System.out.println("Cita notificada");
            }else{
                System.out.println("Fecha anterior");
            }
        }
    }

}
