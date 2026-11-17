package ma.ensa.gestionpedagogique.dto;
import jakarta.validation.constraints.NotNull;
public record RegistrationRequest(@NotNull Long studentId,@NotNull Long courseId) {}
