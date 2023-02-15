package com.example.spring_cinema.services;

import com.example.spring_cinema.model.Movie;
import com.example.spring_cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    private List<Movie> listOfMovies = new ArrayList<Movie>();

//method to add a movie
    public void addMovie(Movie movie){
        this.listOfMovies.add(movie);
        movieRepo.save(movie);
    }

//method to return all movies
    public List<Movie> getAllMovies(){
        List<Movie> listOfMovies = movieRepo.findAll();
        return listOfMovies;
    }

//method to return all movies shorter than a length
    public List<Movie> getAllMoviesShorterThan(Integer dur){

        //Can simplift this using JPARepository, making a new method in the movieRepo for database queries
        return movieRepo.findByDurationLessThan(dur);

//        ArrayList<Movie> moviesToReturn = new ArrayList<>();
//        List<Movie> listOfMovies = movieRepo.findAll();
//        for (Movie m:listOfMovies){
//            if (m.getDuration()<dur){
//                moviesToReturn.add(m);
//            }
//        }
//        return moviesToReturn;
    }

//method to return a movie by it's id
    public Movie getMovieById(int id){
        Movie movie = movieRepo.findById(id).get();
        return movie;
    }

//method to update movie
    public void update(int id,Movie movie){
        movieRepo.deleteById(id);
        movieRepo.save(movie);
    }

//method to delete movie
    public void delete(int id){
        Movie movieToRemove = movieRepo.findById(id).get();
        this.listOfMovies.remove(movieToRemove);
        movieRepo.deleteById(id);
    }
}
