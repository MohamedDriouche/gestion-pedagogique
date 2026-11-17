package ma.ensa.gestionpedagogique.service;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.repository.*;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service public class ExamService {
private final ExamRepository repo;private final CourseService courses;private final GradeRepository grades;
public ExamService(ExamRepository r,CourseService c,GradeRepository g){repo=r;courses=c;grades=g;}
public Exam get(Long id){return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Examen introuvable"));}
public List<Exam> all(){return repo.findAll();}
@Transactional public Exam create(Exam e){courses.get(e.getCourseId());e.setId(null);return repo.save(e);}
@Transactional public Exam update(Long id,Exam input){Exam e=get(id);courses.get(input.getCourseId());if(grades.existsByExamId(id)&&!e.getCourseId().equals(input.getCourseId()))throw new ApiException(HttpStatus.CONFLICT,"Impossible de changer le module d'un examen noté");e.setCourseId(input.getCourseId());e.setDate(input.getDate());return repo.save(e);}
@Transactional public void delete(Long id){get(id);if(grades.existsByExamId(id))throw new ApiException(HttpStatus.CONFLICT,"Examen possédant des notes");repo.deleteById(id);}
}
