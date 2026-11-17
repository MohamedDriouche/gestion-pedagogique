package ma.ensa.gestionpedagogique.controller;
import ma.ensa.gestionpedagogique.entity.Course;
import ma.ensa.gestionpedagogique.service.CourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
@RestController @RequestMapping("/api/courses") public class CourseController {
private final CourseService service;
public CourseController(CourseService service){this.service=service;}
@GetMapping public List<Course> all(){return service.all();}
@GetMapping("/{id}") public Course get(@PathVariable Long id){return service.get(id);}
@PostMapping public ResponseEntity<Course> create(@Valid @RequestBody Course body){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));}
@PutMapping("/{id}") public Course update(@PathVariable Long id,@Valid @RequestBody Course body){return service.update(id,body);}
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
