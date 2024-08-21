package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.config.RestTemplate;
import br.com.unicesumar.magic.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final String API_URL = "https://api.scryfall.com/cards";
    private final String API_URL_NAMED = "/named?fuzy=";

    @Autowired
    private RestTemplate restTemplate;

    public Card getCommanderCard(String name){
        return restTemplate.getForObject(API_URL + API_URL_NAMED + name, String.class);
    }
}
