package cl.devops.innovatech_backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class HealthController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("message", "Backend Innovatech funcionando correctamente");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }
}