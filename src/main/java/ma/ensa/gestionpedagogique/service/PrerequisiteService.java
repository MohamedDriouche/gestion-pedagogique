package ma.ensa.gestionpedagogique.service;

import ma.ensa.gestionpedagogique.entity.CourseEquivalence;
import ma.ensa.gestionpedagogique.entity.CoursePrerequisite;
import ma.ensa.gestionpedagogique.entity.Exam;
import ma.ensa.gestionpedagogique.exception.ApiException;
import ma.ensa.gestionpedagogique.repository.CourseEquivalenceRepository;
import ma.ensa.gestionpedagogique.repository.CoursePrerequisiteRepository;
import ma.ensa.gestionpedagogique.repository.ExamRepository;
import ma.ensa.gestionpedagogique.repository.GradeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service public class PrerequisiteService {
private final CoursePrerequisiteRepository prerequisites;private final CourseEquivalenceRepository equivalences;
private final GradeRepository grades;private final ExamRepository exams;private final CourseService courses;private final StudentService students;
public PrerequisiteService(CoursePrerequisiteRepository p, CourseEquivalenceRepository eq, GradeRepository g, ExamRepository ex, CourseService c, StudentService s){prerequisites=p;equivalences=eq;grades=g;exams=ex;courses=c;students=s;}
public List<CoursePrerequisite> forCourse(Long courseId){courses.get(courseId);return prerequisites.findByCourseId(courseId);}
@Transactional public CoursePrerequisite add(Long courseId,Long requiredId){courses.get(courseId);courses.get(requiredId);
if(courseId.equals(requiredId))throw new ApiException(HttpStatus.BAD_REQUEST,"Un module ne peut pas être son propre prérequis");
if(prerequisites.existsByCourseIdAndPrerequisiteCourseId(courseId,requiredId))throw new ApiException(HttpStatus.CONFLICT,"Prérequis déjà défini");
if(reaches(requiredId,courseId,new HashSet<>()))throw new ApiException(HttpStatus.CONFLICT,"Cycle de prérequis interdit");
CoursePrerequisite p=new CoursePrerequisite();p.setCourseId(courseId);p.setPrerequisiteCourseId(requiredId);return prerequisites.save(p);}
private boolean reaches(Long start,Long target,Set<Long> seen){if(start.equals(target))return true;if(!seen.add(start))return false;
for(CoursePrerequisite p:prerequisites.findByCourseId(start))if(reaches(p.getPrerequisiteCourseId(),target,seen))return true;return false;}
@Transactional public void delete(Long courseId,Long requiredId){courses.get(courseId);CoursePrerequisite p=prerequisites.findByCourseId(courseId).stream().filter(x->x.getPrerequisiteCourseId().equals(requiredId)).findFirst().orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Prérequis introuvable"));prerequisites.delete(p);}
@Transactional public CourseEquivalence recognize(Long studentId,Long requiredId){students.get(studentId);courses.get(requiredId);
if(equivalences.existsByStudentIdAndPrerequisiteCourseId(studentId,requiredId))throw new ApiException(HttpStatus.CONFLICT,"Équivalence déjà reconnue");
CourseEquivalence e=new CourseEquivalence();e.setStudentId(studentId);e.setPrerequisiteCourseId(requiredId);return equivalences.save(e);}
public void validate(Long studentId,Long courseId){for(CoursePrerequisite p:prerequisites.findByCourseId(courseId)){
Long requiredId=p.getPrerequisiteCourseId();if(equivalences.existsByStudentIdAndPrerequisiteCourseId(studentId,requiredId))continue;
List<Long> examIds=exams.findByCourseId(requiredId).stream().map(Exam::getId).toList();
boolean passed=grades.findByStudentId(studentId).stream().anyMatch(g->examIds.contains(g.getExamId())&&g.getValue().compareTo(BigDecimal.TEN)>=0);
if(!passed)throw new ApiException(HttpStatus.CONFLICT,"Prérequis non validé ou absent : module "+requiredId);
}}
}
