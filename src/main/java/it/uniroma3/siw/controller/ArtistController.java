package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.ArtistValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArtistController {
    @Autowired
    private ArtistValidator artistValidator;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/indexArtist")
    public String indexArtist(){return "indexArtist.html";}

    @GetMapping("/artists")
    public String showArtists(Model model) {
        model.addAttribute("artists", artistService.getArtists());
        return "artists.html";
    }

    @GetMapping("/admin/formNewArtist")
    public String formNewArtist(Model model) {
        model.addAttribute("artist", new Artist());
        return "admin/formNewArtist";
    }

    @PostMapping("/admin/artist")
    public String newArtist(@Valid @RequestParam("dataN")String dataN, @RequestParam("dataM")String dataM, @ModelAttribute("artist") Artist artist, BindingResult bindingResult, Model model){
        Artist artistDaVerificare = artistService.setNewArtist(artist, dataN, dataM);
        artistValidator.validate(artistDaVerificare, bindingResult);
        if(!bindingResult.hasErrors()){
            artistService.saveArtist(artistDaVerificare);
            model.addAttribute("artist", artistDaVerificare);
            return "artist.html";
        } else {
            return "admin/formNewArtist";
        }
    }

    @GetMapping("/artists/{idArtist}")
    public String getArtist(@PathVariable("idArtist")Long idA, Model model){
        Artist artist = artistService.getArtist(idA);
        model.addAttribute("artist", artist);
        model.addAttribute("movies_registi", movieService.getMoviesDaRegista(artist));
        model.addAttribute("movies_partecipazione", movieService.getMoviesPartecipazione(artist));
        return "artist.html";
    }
}
