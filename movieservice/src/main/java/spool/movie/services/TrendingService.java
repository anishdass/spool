package spool.movie.services;


import java.util.Set;

public interface TrendingService {
    void incrementMovieScore(String movieId);

    Set<String> getTrendingMovies();
}
