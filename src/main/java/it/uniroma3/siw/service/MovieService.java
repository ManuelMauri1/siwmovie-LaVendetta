package it.uniroma3.siw.service;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.MovieRepository;
import it.uniroma3.siw.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private NewsRepository newsRepository;

    @Transactional
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public Movie setDirectorToMovie(Long idArtist, Long idMovie) {
        Movie movie = getMovie(idMovie);
        Artist regista = artistService.getArtist(idArtist);
        movie.setRegista(regista);
        regista.getFilm_diretti().add(movie);
        movieRepository.save(movie);
        artistService.saveArtist(regista);
        return movie;
    }

    @Transactional
    public List<Movie> searchMovies(Integer anno) {
        return movieRepository.findByAnno(anno);
    }

    @Transactional
    public void saveNewMovie(MultipartFile file, Movie movie) throws IOException {
        Image image = new Image(file.getBytes());
        imageService.saveImage(image);
        movie.setImage(image);
        movieRepository.save(movie);
    }

    @Transactional
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).get();
    }

    @Transactional
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie addActorToMovie(Long idA, Long idM) {
        Movie movie = getMovie(idM);
        Artist actor = artistService.getArtist(idA);
        movie.getAttori().add(actor);
        actor.getPartecipazione_film().add(movie);
        saveMovie(movie);
        return movie;
    }

    @Transactional
    public Movie removeActorFromMovie(Long idA, Long idM) {
        Movie movie = getMovie(idM);
        Artist actor = artistService.getArtist(idA);
        movie.getAttori().remove(actor);
        actor.getPartecipazione_film().remove(movie);
        saveMovie(movie);
        return movie;
    }

    @Transactional
    public void addNotizia(News newsDaInserire, Movie movie) {
        if (movie.getNotizie().isEmpty()) {
            movie.getNotizie().add(newsDaInserire);
        } else {
            Iterator<News> iterator = movie.getNotizie().iterator();
            while (iterator.hasNext()) {
                News newsAttuale = iterator.next();
                if (newsAttuale.getAutore().equals(newsDaInserire.getAutore())) {
                    newsRepository.delete(newsAttuale);
                    iterator.remove();
                }
            }
            movie.getNotizie().add(newsDaInserire);
        }
    }

    @Transactional
    public List<Movie> getMoviesDaRegista(Artist regista) {
        return movieRepository.findAllByRegista(regista);
    }

    @Transactional
    public List<Movie> getMoviesPartecipazione(Artist attore) {
        return movieRepository.findAllByAttoriContaining(attore);
    }
}