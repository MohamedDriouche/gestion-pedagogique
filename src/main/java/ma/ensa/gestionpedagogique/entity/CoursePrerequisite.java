package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;

@Entity @Table(name="course_prerequisites",uniqueConstraints=@UniqueConstraint(columnNames={"course_id","prerequisite_course_id"}))
public class CoursePrerequisite {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(name="course_id",nullable=false) private Long courseId;
@Column(name="prerequisite_course_id",nullable=false) private Long prerequisiteCourseId;
public Long getId(){return id;} public Long getCourseId(){return courseId;} public void setCourseId(Long v){courseId=v;}
public Long getPrerequisiteCourseId(){return prerequisiteCourseId;} public void setPrerequisiteCourseId(Long v){prerequisiteCourseId=v;}
}
