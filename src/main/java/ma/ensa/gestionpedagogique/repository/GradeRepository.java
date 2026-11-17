package ma.ensa.gestionpedagogique.repository;
import ma.ensa.gestionpedagogique.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GradeRepository extends JpaRepository<Grade,Long> {
boolean existsByExamIdAndStudentId(Long examId,Long studentId);
boolean existsByExamIdAndStudentIdAndIdNot(Long examId,Long studentId,Long id);
boolean existsByStudentId(Long studentId);
boolean existsByExamId(Long examId);
java.util.List<Grade> findByStudentId(Long studentId);
}
