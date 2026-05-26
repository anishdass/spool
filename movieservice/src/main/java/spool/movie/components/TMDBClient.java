package spool.movie.components;

import spool.movie.configs.TMDBConfig;
import spool.movie.dto.TMDBResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class TMDBClient {
    private final TMDBConfig config;

    private final RestClient restClient = RestClient.create();

    public TMDBResponseDTO getPopularMovies(){
        return restClient.get()
                .uri(
                        config.getUrl()
                        + "/movie/popular?api_key="
                        + config.getKey()
                )
                .retrieve()
                .body(TMDBResponseDTO.class);
    }
}
