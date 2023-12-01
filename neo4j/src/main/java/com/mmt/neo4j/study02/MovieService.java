package com.mmt.neo4j.study02;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Mono<MovieEntity> createOrUpdateMovie(MovieEntity newMovie) {
        return movieRepository.save(newMovie);
    }
    @Transactional
    public Flux<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }
    @Transactional
    public Mono<MovieEntity> byTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }
    @Transactional
    public Mono<Void> delete(String id) {
        return movieRepository.deleteById(id);
    }

}
