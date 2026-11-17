package ma.ensa.gestionpedagogique.service;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.repository.*;
import ma.ensa.gestionpedagogique.dto.GradeRequest;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service public class GradeService {
private final GradeRepository repo;private final ExamService exams;private final StudentService students;private final RegistrationRepository registrations;
public GradeService(GradeRepository r,ExamService e,StudentService s,RegistrationRepository reg){repo=r;exams=e;students=s;registrations=reg;}
public List<Grade> all(){return repo.findAll();}
public List<Grade> byStudent(Long id){students.get(id);return repo.findByStudentId(id);}
public Grade get(Long id){return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Note introuvable"));}
private void check(GradeRequest request){students.get(request.studentId());Exam exam=exams.get(request.examId());if(!registrations.existsByStudentIdAndCourseIdAndStatus(request.studentId(),exam.getCourseId(),"ACTIVE"))throw new ApiException(HttpStatus.CONFLICT,"Étudiant non inscrit au module de l'examen");}
@Transactional public Grade create(GradeRequest request){check(request);if(repo.existsByExamIdAndStudentId(request.examId(),request.studentId()))throw new ApiException(HttpStatus.CONFLICT,"Note déjà saisie");Grade g=new Grade();g.setExamId(request.examId());g.setStudentId(request.studentId());g.setValue(request.value());return repo.save(g);}
@Transactional public Grade update(Long id,GradeRequest request){Grade g=get(id);check(request);if(repo.existsByExamIdAndStudentIdAndIdNot(request.examId(),request.studentId(),id))throw new ApiException(HttpStatus.CONFLICT,"Note déjà saisie");g.setExamId(request.examId());g.setStudentId(request.studentId());g.setValue(request.value());return repo.save(g);}
@Transactional public void delete(Long id){get(id);repo.deleteById(id);}
}
