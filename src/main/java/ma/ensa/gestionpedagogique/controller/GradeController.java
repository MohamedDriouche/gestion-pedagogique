package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Grade;
import ma.ensa.gestionpedagogique.service.GradeService;
import ma.ensa.gestionpedagogique.dto.GradeRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/grades") public class GradeController {
private final GradeService service;
public GradeController(GradeService service){this.service=service;}
@GetMapping public List<Grade> all(){return service.all();}
@GetMapping("/{id}") public Grade get(@PathVariable Long id){return service.get(id);}
@GetMapping("/students/{id}") public List<Grade> byStudent(@PathVariable Long id){return service.byStudent(id);}
@PostMapping public ResponseEntity<Grade> create(@Valid @RequestBody GradeRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
@PutMapping("/{id}") public Grade update(@PathVariable Long id,@Valid @RequestBody GradeRequest r){return service.update(id,r);}
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
