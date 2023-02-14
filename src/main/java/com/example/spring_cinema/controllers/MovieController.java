package com.example.spring_cinema.controllers;

import com.example.spring_cinema.model.Movie;
import com.example.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

//    @GetMapping
//    public ResponseEntity<List<Movie>> getMovie(){
//        List<Movie> moviesToReturn = movieService.getAllMovies();
//        return new ResponseEntity<>(moviesToReturn,HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMoviesShorterThan(
            @RequestParam Optional<Integer> duration
            ){
        if (duration.isPresent()) {
            List<Movie> moviesToReturn = movieService.getAllMoviesShorterThan(duration.get());
            return new ResponseEntity<>(moviesToReturn, HttpStatus.OK);
        } else {
            List<Movie> moviesToReturn = movieService.getAllMovies();
            return new ResponseEntity<>(moviesToReturn,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        Movie movieToReturn = movieService.getMovieById(id);
        return new ResponseEntity<>(movieToReturn,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added", HttpStatus.CREATED);
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<String> updateMovie(
            @PathVariable int id,
            @RequestBody Movie movie){
        movieService.update(id,movie);
        return new ResponseEntity<>("Movie updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id){
        movieService.delete(id);
        return new ResponseEntity<>("Movie has been deleted",HttpStatus.OK);
    }
}
