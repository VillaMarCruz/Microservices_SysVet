package xyz.vet.microservice0apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.vet.microservice0apigateway.dto.EmailDto;

@FeignClient(
        name = "email-service",
        path = "/api/",
        url = "${email.service.url}",
        configuration = FeignConfiguration.class
)
public interface EmailServiceRequest {
    @PostMapping("email/send")
    void createEmail(@RequestBody EmailDto emailDto);

}
