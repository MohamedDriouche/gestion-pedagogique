package ma.ensa.gestionpedagogique.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler {
@ExceptionHandler(ApiException.class) public ResponseEntity<Map<String,Object>> api(ApiException e){return ResponseEntity.status(e.getStatus()).body(Map.of("error",e.getMessage(),"status",e.getStatus().value()));}
@ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,Object>> invalid(MethodArgumentNotValidException e){List<String> errors=e.getBindingResult().getFieldErrors().stream().map(f->f.getField()+": "+f.getDefaultMessage()).toList();return ResponseEntity.badRequest().body(Map.of("error","Validation invalide","details",errors));}
@ExceptionHandler(DataIntegrityViolationException.class) public ResponseEntity<Map<String,Object>> integrity(DataIntegrityViolationException e){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","Contrainte de données violée"));}
}
