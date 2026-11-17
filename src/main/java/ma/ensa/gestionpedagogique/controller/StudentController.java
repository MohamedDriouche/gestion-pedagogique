package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Student;
import ma.ensa.gestionpedagogique.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/students") public class StudentController {
private final StudentService service;
public StudentController(StudentService service){this.service=service;}
@GetMapping public List<Student> all(){return service.all();}
@GetMapping("/{id}") public Student get(@PathVariable Long id){return service.get(id);}
@PostMapping public ResponseEntity<Student> create(@Valid @RequestBody Student body){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));}
@PutMapping("/{id}") public Student update(@PathVariable Long id,@Valid @RequestBody Student body){return service.update(id,body);}
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
