package ma.ensa.gestionpedagogique;
import ma.ensa.gestionpedagogique.dto.RegistrationRequest;
import ma.ensa.gestionpedagogique.entity.*;
import ma.ensa.gestionpedagogique.service.*;
import ma.ensa.gestionpedagogique.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional class RegistrationServiceTest {
@Autowired StudentService students; @Autowired CourseService courses; @Autowired RegistrationService registrations;
@Test void registrationDuplicateAndCapacity(){Student s=new Student();s.setName("Alice");s.setEmail("alice@test.com");s.setProgram("GI");s=students.create(s);Course c=new Course();c.setCode("JAVA101");c.setTitle("Java");c.setCapacity(1);c=courses.create(c);RegistrationRequest request=new RegistrationRequest(s.getId(),c.getId());assertEquals("ACTIVE",registrations.create(request).getStatus());assertThrows(ApiException.class,()->registrations.create(request));Student s2=new Student();s2.setName("Bob");s2.setEmail("bob@test.com");s2.setProgram("GI");s2=students.create(s2);Long id=s2.getId();Long courseId=c.getId();assertThrows(ApiException.class,()->registrations.create(new RegistrationRequest(id,courseId)));}
}
