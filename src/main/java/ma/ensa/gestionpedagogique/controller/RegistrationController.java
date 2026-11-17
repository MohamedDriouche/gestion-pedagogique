package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Registration;
import ma.ensa.gestionpedagogique.service.RegistrationService;
import ma.ensa.gestionpedagogique.dto.RegistrationRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/registrations") public class

RegistrationController {
private final RegistrationService service;
public RegistrationController(RegistrationService service){this.service=service;}
@GetMapping public List<Registration> all(){return service.all();}
@PostMapping public ResponseEntity<Registration> create(@Valid @RequestBody RegistrationRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
@GetMapping("/students/{studentId}") public List<Registration> byStudent(@PathVariable Long studentId){return service.byStudent(studentId);}
}
