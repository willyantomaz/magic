package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.enums.CardType;
import br.com.unicesumar.magic.repository.CardRepository;
import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.service.CardService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DeckRepository deckRepository;

    private Deck deck;

    @GetMapping("/commander")
    @PermitAll
    public ResponseEntity<Card> getCommander(@RequestBody Card name){

        Card retorno = cardService.getCommanderCard(name.getName());


        if(retorno.getCardType().equals(CardType.COMMANDER)){
            retorno.setResponse("Carta Adicionada com sucesso");
            //cardRepository.save(retorno);
            deck = new Deck();
            this.deck.setCommander(retorno);
            this.deck.setCards(cardService.getCommonCard());
            deckRepository.save(this.deck);

            return ResponseEntity.ok(retorno);
        }
        retorno.setResponse("Essa carta não pode ser Commander");
        return ResponseEntity.badRequest().body(retorno);


    }
}
