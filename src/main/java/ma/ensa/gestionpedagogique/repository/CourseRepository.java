package ma.ensa.gestionpedagogique.repository;
import ma.ensa.gestionpedagogique.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course,Long> {
boolean existsByCodeIgnoreCase(String code);
boolean existsByCodeIgnoreCaseAndIdNot(String code,Long id);
}
