package it.uniroma3.siw.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;
    private LocalDate dataNascita;
    private LocalDate dataMorte;

    @ManyToMany(mappedBy = "attori")
    private List<Movie> partecipazione_film;

    @OneToMany(mappedBy = "regista")
    private List<Movie> film_diretti;

    public Artist(){
        partecipazione_film = new ArrayList<>();
        film_diretti = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(nome, artist.nome) && Objects.equals(cognome, artist.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }

}
