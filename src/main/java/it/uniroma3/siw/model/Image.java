package it.uniroma3.siw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] bytes;

    @Lob
    private String base64Image;

    public Image(byte[] bytes){
        this.bytes = bytes;
        this.setBase64Image(Base64.getEncoder().encodeToString(this.bytes));
    }
}
