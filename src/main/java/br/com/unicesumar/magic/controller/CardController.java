package br.com.unicesumar.magic.controller;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/commander")
    public Card getCommander(@PathVariable String name){
            return cardService.getCommanderCard(name);
    }
}
