package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
