package spool.movie.dto;

import java.util.UUID;

public record MovieResponseDTO(
        UUID id,
        String title,
        String genre,
        Double rating,
        Integer releaseYear
) {
}
