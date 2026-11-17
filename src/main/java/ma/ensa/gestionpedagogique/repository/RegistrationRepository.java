package ma.ensa.gestionpedagogique.repository;
import ma.ensa.gestionpedagogique.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
long countByCourseIdAndStatus(Long courseId,String status);
boolean existsByStudentIdAndCourseIdAndStatus(Long studentId,Long courseId,String status);
java.util.List<Registration> findByStudentId(Long studentId);
boolean existsByStudentId(Long studentId);
boolean existsByCourseId(Long courseId);
}
