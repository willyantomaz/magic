package br.com.unicesumar.magic.controller;

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

    @Autowired
    private DeckService deckService;

    private Deck deck;

    @GetMapping("/commander")
    public ResponseEntity getCommander(@RequestBody Card name, @RequestParam int qntdCard) {

        Card retorno = cardService.getCommanderCard(name.getName());


        if (retorno.getCardType().equals(CardType.COMMANDER)) {
            deck = new Deck();
            this.deck.setCommander(retorno);
            this.deck.setCards(cardService.getCommonCard(qntdCard, retorno.getColors()));

            deckService.saveDeck(this.deck);
            saveCardsToFile(this.deck, "src/main/resources/deck.json");
            retorno.setResponse("Carta Adicionada com sucesso");

            return ResponseEntity.ok(retorno);
        }
        retorno.setResponse("Essa carta não pode ser Commander");
        return ResponseEntity.badRequest().body(retorno);


    }

    public void saveCardsToFile(Deck deck, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), deck);
            System.out.println("Cartas salvas com sucesso em " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar cartas em arquivo JSON");
        }
    }
}
