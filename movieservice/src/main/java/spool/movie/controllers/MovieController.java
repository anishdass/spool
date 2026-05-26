package spool.movie.controllers;

import spool.movie.dto.MovieRequestDTO;
import spool.movie.dto.MovieResponseDTO;
import spool.movie.events.MovieWatchedEvent;
import spool.movie.kafka.producers.MovieEventProducer;
import spool.movie.services.MovieService;
import spool.movie.services.TrendingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final TrendingService trendingService;
    private final MovieEventProducer movieEventProducer;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getMovies(
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieService.findAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieService.findMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO requestDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.saveMovie(requestDTO));
    }

    @PostMapping("/import")
    public ResponseEntity<String> importMovies(){
        movieService.importPopularMovies();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Movies imported successfully");
    }

    @PostMapping("/{id}/watch")
    public ResponseEntity<String> watchMovie(@PathVariable UUID movieId,
                                             @RequestParam UUID userId){
        movieEventProducer.publishMovieWatchedEvent(
                new MovieWatchedEvent(
                        movieId,
                        userId,
                        LocalDateTime.now()
                )
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Movie Watched");
    }

    @GetMapping("/trending")
    public ResponseEntity<Set<String>> getTrendingMovies(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(trendingService.getTrendingMovies());
    }

}
