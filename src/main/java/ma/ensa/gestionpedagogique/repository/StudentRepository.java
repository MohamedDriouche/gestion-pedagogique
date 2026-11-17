package ma.ensa.gestionpedagogique.repository;
import ma.ensa.gestionpedagogique.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student,Long> {
boolean existsByEmailIgnoreCase(String email);
boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id);
}
