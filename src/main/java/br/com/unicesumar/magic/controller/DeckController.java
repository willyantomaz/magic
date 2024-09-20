package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping("/all")
    public ResponseEntity<List<Deck>> getDecks () {
        return ResponseEntity.ok(this.deckService.findAllDecks());
    }


}
