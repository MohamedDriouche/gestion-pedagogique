package ma.ensa.gestionpedagogique.repository;

import ma.ensa.gestionpedagogique.entity.CoursePrerequisite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite,Long>{
List<CoursePrerequisite> findByCourseId(Long courseId);
boolean existsByCourseIdAndPrerequisiteCourseId(Long courseId,Long prerequisiteCourseId);
boolean existsByCourseIdOrPrerequisiteCourseId(Long courseId,Long prerequisiteCourseId);
}
