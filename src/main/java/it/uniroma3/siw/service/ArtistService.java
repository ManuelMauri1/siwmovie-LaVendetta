package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    @Transactional
    public void saveArtist(Artist artist){
        artistRepository.save(artist);
    }
    @Transactional
    public Artist setNewArtist(Artist artist, String dataN, String dataM){
        Artist artistSettato = new Artist(artist.getNome(),artist.getCognome());
        artistSettato.setDataNascita(LocalDate.parse(dataN));
        if (!dataM.isEmpty())
            artistSettato.setDataMorte(LocalDate.parse(dataM));
        return artistSettato;
    }

    @Transactional
    public Artist getArtist(Long id){
        return artistRepository.findById(id).get();
    }

    @Transactional
    public List<Artist> getArtists(){
        return (List<Artist>) artistRepository.findAll();
    }

    @Transactional
    public List<Artist> notMovieActors(Movie movie){
        List<Artist> notMovieActors = (List<Artist>) artistRepository.findAll();
        notMovieActors.removeAll(movie.getAttori());
        return notMovieActors;
    }
}
