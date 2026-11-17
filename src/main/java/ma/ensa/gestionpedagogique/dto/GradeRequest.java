package ma.ensa.gestionpedagogique.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record GradeRequest(@NotNull Long examId,@NotNull Long studentId,@NotNull @DecimalMin("0.0") @DecimalMax("20.0") BigDecimal value) {}
