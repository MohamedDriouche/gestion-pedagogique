package ma.ensa.gestionpedagogique.service;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.repository.*;
import ma.ensa.gestionpedagogique.dto.RegistrationRequest;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import java.util.*;
@Service public class RegistrationService {
private final RegistrationRepository repo;private final StudentService students;private final CourseService courses;private final EntityManager em;
public RegistrationService(RegistrationRepository r,StudentService s,CourseService c,EntityManager em){repo=r;students=s;courses=c;this.em=em;}
public List<Registration> all(){return repo.findAll();}
public List<Registration> byStudent(Long id){students.get(id);return repo.findByStudentId(id);}
@Transactional public Registration create(RegistrationRequest request){students.get(request.studentId());Course course=em.find(Course.class,request.courseId(),LockModeType.PESSIMISTIC_WRITE);if(course==null)throw new ApiException(HttpStatus.NOT_FOUND,"Module introuvable");if(repo.existsByStudentIdAndCourseIdAndStatus(request.studentId(),request.courseId(),"ACTIVE"))throw new ApiException(HttpStatus.CONFLICT,"Déjà inscrit à ce module");if(repo.countByCourseIdAndStatus(course.getId(),"ACTIVE")>=course.getCapacity())throw new ApiException(HttpStatus.CONFLICT,"Module complet");Registration r=new Registration();r.setStudentId(request.studentId());r.setCourseId(request.courseId());r.setStatus("ACTIVE");return repo.save(r);}
}
