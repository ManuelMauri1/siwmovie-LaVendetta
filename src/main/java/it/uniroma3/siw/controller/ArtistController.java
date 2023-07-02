package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.ArtistValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArtistController {
    @Autowired
    private ArtistValidator artistValidator;
    @Autowired
    private ArtistService artistService;

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
        artistValidator.validate(artist, bindingResult);
        if(!bindingResult.hasErrors()){
            artistService.saveNewArtist(artist, dataN, dataM);
            model.addAttribute("artist", artist);
            return "artist.html";
        } else {
            return "admin/formNewArtist";
        }
    }

    @GetMapping("/artists/{idArtist}")
    public String getArtist(@PathVariable("idArtist")Long idA, Model model){
        model.addAttribute("artist", artistService.getArtist(idA));
        return "artist.html";
    }
}
