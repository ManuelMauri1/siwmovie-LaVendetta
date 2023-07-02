package it.uniroma3.siw.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer voto;
    private String titolo;
    private String descrizione;

    @OneToOne
    private Credentials user;
}