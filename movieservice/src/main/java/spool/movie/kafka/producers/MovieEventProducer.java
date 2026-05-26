package spool.movie.kafka.producers;

import spool.movie.events.MovieWatchedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieEventProducer {
    private static final String TOPIC = "movie-watched";
    private final KafkaTemplate<String, MovieWatchedEvent> kafkaTemplate;

    public void publishMovieWatchedEvent(MovieWatchedEvent event){
        kafkaTemplate.send(
                TOPIC,
                event.getMovieId().toString(),
                event
        );
    }
}
