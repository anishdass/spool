package com.example.demo.services;


import java.util.Set;

public interface TrendingService {
    void incrementMovieScore(String movieId);

    Set<String> getTrendingMovies();
}
