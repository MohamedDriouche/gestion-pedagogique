package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Exam;
import ma.ensa.gestionpedagogique.service.ExamService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/exams") public class ExamController {
private final ExamService service;
public ExamController(ExamService service){this.service=service;}
@GetMapping public List<Exam> all(){return service.all();}
@GetMapping("/{id}") public Exam get(@PathVariable Long id){return service.get(id);}
@PostMapping public ResponseEntity<Exam> create(@Valid @RequestBody Exam body){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));}
@PutMapping("/{id}") public Exam update(@PathVariable Long id,@Valid @RequestBody Exam body){return service.update(id,body);}
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
