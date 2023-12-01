package com.mmt.neo4j.study02;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping
    public Mono<MovieEntity> createOrUpdateMovie(@RequestBody MovieEntity newMovie) {
        return movieService.createOrUpdateMovie(newMovie);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEntity> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/by-title")
    public Mono<MovieEntity> byTitle(@RequestParam String title) {
        return movieService.byTitle(title);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return movieService.delete(id);
    }

}