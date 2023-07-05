package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.News;
import it.uniroma3.siw.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    CredentialsService credentialsService;

    @Transactional
    public Movie newNews(String autore, Long idM, News news, Integer voto) {
        Movie movie = movieService.getMovie(idM);
        news.setVoto(voto);
        news.setAutore(autore);
        saveNews(news);
        movieService.addNotizia(news, movie);
        movieService.saveMovie(movie);
        return movie;
    }

    @Transactional
    public void saveNews(News news) {
        newsRepository.save(news);
    }
}
