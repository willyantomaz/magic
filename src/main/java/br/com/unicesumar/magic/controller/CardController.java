package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.dto.DeckCreatorDTO;
import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.enums.CardType;
import br.com.unicesumar.magic.repository.CardRepository;
import br.com.unicesumar.magic.service.CardService;
import br.com.unicesumar.magic.service.DeckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/commander")
    public ResponseEntity getCommander(@RequestBody DeckCreatorDTO creatorDTO, @RequestParam int qntdCard, @RequestHeader String Authorization) {
        return ResponseEntity.ok(this.cardService.getCommander(creatorDTO, qntdCard, Authorization));
    }
}
