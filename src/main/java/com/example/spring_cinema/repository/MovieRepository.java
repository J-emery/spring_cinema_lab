package com.example.spring_cinema.repository;

import com.example.spring_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    public List<Movie> findByDurationLessThan(int dur);
}
