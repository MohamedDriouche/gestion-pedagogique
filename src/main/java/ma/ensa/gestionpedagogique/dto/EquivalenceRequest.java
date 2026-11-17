package ma.ensa.gestionpedagogique.dto;
import jakarta.validation.constraints.NotNull;

public record EquivalenceRequest(@NotNull Long studentId, @NotNull Long prerequisiteCourseId){}
