package spool.movie.services;

import spool.movie.dto.MovieRequestDTO;
import spool.movie.dto.MovieResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
public interface MovieService {

    List<MovieResponseDTO> findAllMovies();

    MovieResponseDTO findMovieById(UUID movieId);

    MovieResponseDTO saveMovie(MovieRequestDTO movieRequestDTO);

    void importPopularMovies();
}
