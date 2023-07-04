package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ArtistValidator implements Validator {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Artist.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotBlank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotBlank");

        Artist artist = (Artist) o;
        if(artistRepository.existsByNomeAndCognome(artist.getNome(), artist.getCognome()))
            errors.reject("artist.duplicati");
        if (artist.getDataMorte() != null && artist.getDataNascita().isAfter(artist.getDataMorte()))
            errors.reject("artist.dataNascita.dopoDataMorte");
    }
}
