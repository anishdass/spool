package com.example.demo.controllers;

import com.example.demo.dto.MovieRequestDTO;
import com.example.demo.dto.MovieResponseDTO;
import com.example.demo.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getMovies(){
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

}
