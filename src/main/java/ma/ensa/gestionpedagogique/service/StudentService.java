package ma.ensa.gestionpedagogique.service;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.repository.*;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service public class StudentService {
private final StudentRepository repo; private final RegistrationRepository registrations;private final GradeRepository grades;
public StudentService(StudentRepository r,RegistrationRepository reg,GradeRepository g){repo=r;registrations=reg;grades=g;}
public List<Student> all(){return repo.findAll();}
public Student get(Long id){return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Étudiant introuvable"));}
@Transactional public Student create(Student s){if(repo.existsByEmailIgnoreCase(s.getEmail()))throw new ApiException(HttpStatus.CONFLICT,"E-mail déjà utilisé");s.setId(null);return repo.save(s);}
@Transactional public Student update(Long id,Student input){Student s=get(id);if(repo.existsByEmailIgnoreCaseAndIdNot(input.getEmail(),id))throw new ApiException(HttpStatus.CONFLICT,"E-mail déjà utilisé");s.setName(input.getName());s.setEmail(input.getEmail());s.setProgram(input.getProgram());return repo.save(s);}
@Transactional public void delete(Long id){get(id);if(registrations.existsByStudentId(id)||grades.existsByStudentId(id))throw new ApiException(HttpStatus.CONFLICT,"Étudiant lié à des inscriptions ou notes");repo.deleteById(id);}
}
