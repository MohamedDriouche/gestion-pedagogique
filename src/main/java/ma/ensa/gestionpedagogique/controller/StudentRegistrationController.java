package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Registration;
import ma.ensa.gestionpedagogique.service.RegistrationService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/students") public class StudentRegistrationController {
private final RegistrationService service;
public StudentRegistrationController(RegistrationService service){this.service=service;}
@GetMapping("/{id}/registrations") public List<Registration> byStudent(@PathVariable Long id){return service.byStudent(id);}
}
