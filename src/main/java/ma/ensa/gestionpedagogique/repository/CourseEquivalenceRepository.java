package ma.ensa.gestionpedagogique.repository;
import ma.ensa.gestionpedagogique.entity.CourseEquivalence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEquivalenceRepository extends JpaRepository<CourseEquivalence,Long>{
boolean existsByStudentIdAndPrerequisiteCourseId(Long studentId,Long prerequisiteCourseId);
}
