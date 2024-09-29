package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.dto.DeckDTO;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deck")
public class    DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping("/all")
    public ResponseEntity<List<Deck>> getDecks () {
        return ResponseEntity.ok(this.deckService.findAllDecks());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Deck>> getDecksByUser(@RequestHeader String Authorization) {
        return ResponseEntity.ok(this.deckService.findDecksByUser(Authorization));
    }

    @PostMapping("/import")
    public ResponseEntity<String> importDeck(@RequestBody DeckDTO deckDTO) {
        try {
            deckService.validateAndImportDeck(deckDTO);
            return ResponseEntity.ok("Deck imported successfully for user " + deckDTO.getUser());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
