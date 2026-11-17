package ma.ensa.gestionpedagogique.dto;
import jakarta.validation.constraints.NotNull;

public record PrerequisiteRequest(@NotNull Long prerequisiteCourseId){}
