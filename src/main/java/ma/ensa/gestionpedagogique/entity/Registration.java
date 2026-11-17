package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="registrations")
public class Registration {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@jakarta.validation.constraints.NotNull private Long studentId;
@jakarta.validation.constraints.NotNull private Long courseId;
@jakarta.validation.constraints.NotBlank private String status;
public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public Long getStudentId(){return studentId;}
public void setStudentId(Long value){this.studentId=value;}
public Long getCourseId(){return courseId;}
public void setCourseId(Long value){this.courseId=value;}
public String getStatus(){return status;}
public void setStatus(String value){this.status=value;}
}
