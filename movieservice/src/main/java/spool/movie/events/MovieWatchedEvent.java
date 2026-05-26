package spool.movie.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieWatchedEvent {
    private UUID movieId;
    private UUID userId;
    private LocalDateTime watchedAt;
}
