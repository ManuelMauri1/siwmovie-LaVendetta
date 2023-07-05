package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    public List<Movie> findByAnno(Integer anno);
    public boolean existsByTitoloAndAnno(String titolo, Integer anno);
    public List<Movie> findAll();
    public List<Movie> findAllByRegista(Artist regista);
    public List<Movie> findAllByAttoriContaining(Artist attore);
}
