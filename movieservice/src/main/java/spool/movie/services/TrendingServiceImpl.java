package spool.movie.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TrendingServiceImpl implements TrendingService {

    private static final String TRENDING_KEY="trending_movies";
    private final StringRedisTemplate redisTemplate;

    @Override
    public void incrementMovieScore(String movieId) {
        redisTemplate.opsForZSet()
                .incrementScore(
                        TRENDING_KEY,
                        movieId,
                        1
                );
    }

    @Override
    public Set<String> getTrendingMovies() {
        return redisTemplate.opsForZSet()
                .reverseRange(
                        TRENDING_KEY,
                        0,
                        9
                );
    }
}
