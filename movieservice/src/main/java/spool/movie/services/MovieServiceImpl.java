package spool.movie.services;

import spool.movie.components.TMDBClient;
import spool.movie.dto.MovieRequestDTO;
import spool.movie.dto.MovieResponseDTO;
import spool.movie.dto.TMDBResponseDTO;
import spool.movie.exceptions.MovieNotFoundException;
import spool.movie.models.Movie;
import spool.movie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final TMDBClient tmdbClient;

    public List<MovieResponseDTO> findAllMovies(){


        return movieRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MovieResponseDTO findMovieById(UUID movieId){
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new MovieNotFoundException("Movie not found"));

        return mapToResponse(movie);
    }

    public MovieResponseDTO saveMovie(MovieRequestDTO movieRequestDTO){

        Movie movie = Movie.builder()
                .title(movieRequestDTO.title())
                .genre(movieRequestDTO.genre())
                .rating(movieRequestDTO.rating())
                .releaseYear(movieRequestDTO.releaseYear())
                .build();

        Movie savedMovie=movieRepository.save(movie);

        return mapToResponse(savedMovie);
    }

    @Override
    public void importPopularMovies() {
        TMDBResponseDTO response=
                tmdbClient.getPopularMovies();

        response.getResults()
                .forEach(movieDTO -> {
                    Movie movie=Movie.builder()
                            .title(movieDTO.getTitle())
                            .genre("Unknown")
                            .rating(movieDTO.getVote_average())
                            .releaseYear(
                                    extractYear(
                                    movieDTO.getRelease_date()
                                    ))
                            .build();

                    movieRepository.save(movie);
                });
    }

    private MovieResponseDTO mapToResponse(Movie savedMovie){
        return new MovieResponseDTO(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getGenre(),
                savedMovie.getRating(),
                savedMovie.getReleaseYear()
        );
    }

    private Integer extractYear(String date){

        if(date==null||date.isBlank()){
            return null;
        }

        return Integer.parseInt(date.substring(0, 4));

    }

}
