package spool.movie.dto;

import spool.movie.annotation.IsHalfStep;
import jakarta.validation.constraints.*;

public record MovieRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Genre is required")
        String genre,

        @DecimalMin(value = "0.5")
        @DecimalMax(value = "5")
        @IsHalfStep
        Double rating,

        @Min(value = 1900)
        Integer releaseYear
) {
}
