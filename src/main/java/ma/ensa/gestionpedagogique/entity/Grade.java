package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="grades")
public class Grade {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@jakarta.validation.constraints.NotNull private Long examId;
@jakarta.validation.constraints.NotNull private Long studentId;
@jakarta.validation.constraints.NotNull @jakarta.validation.constraints.DecimalMin("0.0") @jakarta.validation.constraints.DecimalMax("20.0") private java.math.BigDecimal value;
public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public Long getExamId(){return examId;}
public void setExamId(Long value){this.examId=value;}
public Long getStudentId(){return studentId;}
public void setStudentId(Long value){this.studentId=value;}
public java.math.BigDecimal getValue(){return value;}
public void setValue(java.math.BigDecimal value){this.value=value;}
}
