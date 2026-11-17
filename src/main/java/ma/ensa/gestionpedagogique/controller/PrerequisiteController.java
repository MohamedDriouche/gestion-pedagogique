package ma.ensa.gestionpedagogique.controller;

import jakarta.validation.Valid;
import ma.ensa.gestionpedagogique.dto.EquivalenceRequest;
import ma.ensa.gestionpedagogique.dto.PrerequisiteRequest;
import ma.ensa.gestionpedagogique.entity.CourseEquivalence;
import ma.ensa.gestionpedagogique.entity.CoursePrerequisite;
import ma.ensa.gestionpedagogique.service.PrerequisiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController public class PrerequisiteController {
private final PrerequisiteService service;public PrerequisiteController(PrerequisiteService s){service=s;}
@GetMapping("/api/courses/{courseId}/prerequisites") public List<CoursePrerequisite> list(@PathVariable Long courseId){return service.forCourse(courseId);}
@PostMapping("/api/courses/{courseId}/prerequisites") public ResponseEntity<CoursePrerequisite> add(@PathVariable Long courseId,@Valid @RequestBody PrerequisiteRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.add(courseId,r.prerequisiteCourseId()));}
@DeleteMapping("/api/courses/{courseId}/prerequisites/{requiredId}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long courseId,@PathVariable Long requiredId){service.delete(courseId,requiredId);}
@PostMapping("/api/equivalences") public ResponseEntity<CourseEquivalence> recognize(@Valid @RequestBody EquivalenceRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.recognize(r.studentId(),r.prerequisiteCourseId()));}
}
