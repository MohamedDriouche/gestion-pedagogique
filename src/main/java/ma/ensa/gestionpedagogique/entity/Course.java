package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="courses")
public class Course {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@jakarta.validation.constraints.NotBlank private String code;
@jakarta.validation.constraints.NotBlank private String title;
@jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(1) private Integer capacity;
public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public String getCode(){return code;}
public void setCode(String value){this.code=value;}
public String getTitle(){return title;}
public void setTitle(String value){this.title=value;}
public Integer getCapacity(){return capacity;}
public void setCapacity(Integer value){this.capacity=value;}
}
