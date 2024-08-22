package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.config.RestTemplateConfig;
import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static br.com.unicesumar.magic.config.RestTemplateConfig.restTemplate;

@Service
public class CardService {

    private final String API_URL = "https://api.scryfall.com/cards";
    private final String API_URL_NAMED = "/named?fuzzy=";



    private List<Card> cards = new ArrayList<>();

    public Card getCommanderCard(String name)  {

        String url = API_URL + API_URL_NAMED + name;
        Card commander = restTemplate().getForObject(url, Card.class);

        if (commander.getType_line().startsWith("Legendary Creature")) {
            commander.setCardType(CardType.COMMANDER);
        } else {
            commander.setCardType(CardType.COMMON);
        }

        return commander;
    }

    public List<Card> getCommonCard() {
        String urlCardRandom = "/random";
        String url = API_URL + urlCardRandom;
        //try {
            Card common = restTemplate().getForObject(url, Card.class);
            common.setCardType(CardType.COMMON);

            cards.add(common);
            return cards;
//        }catch (Exception e){
//
//            System.out.println(e+"vazio");
//        }


    }
 }



