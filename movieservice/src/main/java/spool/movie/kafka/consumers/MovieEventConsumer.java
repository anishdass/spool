package spool.movie.kafka.consumers;

import spool.movie.events.MovieWatchedEvent;
import spool.movie.services.TrendingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieEventConsumer {
    private final TrendingService trendingService;

    @KafkaListener(
            topics = "movie-watched",
            groupId = "movie-group"
    )

    public void consumeMovieWatchedEvent(MovieWatchedEvent event){
        log.info(
                "Movie watched event received: {}",
                event
        );

        trendingService.incrementMovieScore(
                event.getMovieId().toString()
        );
    }
}
