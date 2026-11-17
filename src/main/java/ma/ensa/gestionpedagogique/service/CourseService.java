package ma.ensa.gestionpedagogique.service;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.repository.*;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service public class CourseService {
private final CourseRepository repo;private final RegistrationRepository registrations;private final ExamRepository exams;
public CourseService(CourseRepository r,RegistrationRepository reg,ExamRepository e){repo=r;registrations=reg;exams=e;}
public List<Course> all(){return repo.findAll();}
public Course get(Long id){return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Module introuvable"));}
@Transactional public Course create(Course c){if(repo.existsByCodeIgnoreCase(c.getCode()))throw new ApiException(HttpStatus.CONFLICT,"Code de module déjà utilisé");c.setId(null);return repo.save(c);}
@Transactional public Course update(Long id,Course input){Course c=get(id);if(repo.existsByCodeIgnoreCaseAndIdNot(input.getCode(),id))throw new ApiException(HttpStatus.CONFLICT,"Code déjà utilisé");long enrolled=registrations.countByCourseIdAndStatus(id,"ACTIVE");if(input.getCapacity()<enrolled)throw new ApiException(HttpStatus.CONFLICT,"Capacité inférieure au nombre d'inscrits");c.setCode(input.getCode());c.setTitle(input.getTitle());c.setCapacity(input.getCapacity());return repo.save(c);}
@Transactional public void delete(Long id){get(id);if(registrations.existsByCourseId(id)||exams.existsByCourseId(id))throw new ApiException(HttpStatus.CONFLICT,"Module lié à des inscriptions ou examens");repo.deleteById(id);}
}
