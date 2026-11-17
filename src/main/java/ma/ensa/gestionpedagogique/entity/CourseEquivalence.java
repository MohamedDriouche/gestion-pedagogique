package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;

@Entity @Table(name="course_equivalences",uniqueConstraints=@UniqueConstraint(columnNames={"student_id","prerequisite_course_id"}))
public class CourseEquivalence {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(name="student_id",nullable=false) private Long studentId;
@Column(name="prerequisite_course_id",nullable=false) private Long prerequisiteCourseId;
public Long getId(){return id;} public Long getStudentId(){return studentId;} public void setStudentId(Long v){studentId=v;}
public Long getPrerequisiteCourseId(){return prerequisiteCourseId;} public void setPrerequisiteCourseId(Long v){prerequisiteCourseId=v;}
}
