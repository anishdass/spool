package spool.movie.dto;

import lombok.Data;

@Data
public class TMDBMovieDTO {
    private String title;
    private Double vote_average;
    private String release_date;
    private String overview;
}
