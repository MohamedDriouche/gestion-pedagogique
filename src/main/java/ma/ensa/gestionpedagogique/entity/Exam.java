package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="exams")
public class Exam {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@jakarta.validation.constraints.NotNull private Long courseId;
@jakarta.validation.constraints.NotNull private java.time.LocalDateTime date;
public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public Long getCourseId(){return courseId;}
public void setCourseId(Long value){this.courseId=value;}
public java.time.LocalDateTime getDate(){return date;}
public void setDate(java.time.LocalDateTime value){this.date=value;}
}
