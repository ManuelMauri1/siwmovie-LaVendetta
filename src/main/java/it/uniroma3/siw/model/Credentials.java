package it.uniroma3.siw.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    public static final String RUOLO_DEFAULT = "DEFAULT";
    public static final String RUOLO_ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String ruolo;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne
    private News recensione;
}
