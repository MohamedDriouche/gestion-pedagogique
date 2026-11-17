package ma.ensa.gestionpedagogique.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name="students")
public class Student {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@jakarta.validation.constraints.NotBlank private String name;
@jakarta.validation.constraints.NotBlank @jakarta.validation.constraints.Email private String email;
@jakarta.validation.constraints.NotBlank private String program;
public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public String getName(){return name;}
public void setName(String value){this.name=value;}
public String getEmail(){return email;}
public void setEmail(String value){this.email=value;}
public String getProgram(){return program;}
public void setProgram(String value){this.program=value;}
}
