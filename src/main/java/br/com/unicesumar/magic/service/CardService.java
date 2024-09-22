package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.entity.ScryfallResponse;
import br.com.unicesumar.magic.enums.CardType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.com.unicesumar.magic.config.RestTemplateConfig.restTemplate;

@Service
public class CardService {

    @Autowired
    private DeckService deckService;

    private final String API_URL = "https://api.scryfall.com/cards";
    private final String API_URL_NAMED = "/named?fuzzy=";
    private final String API_URL_CARDS = "/search?q=color:%s&unique=cards&page=";

    public Card getCommanderCard(String name) {

        String url = API_URL + API_URL_NAMED + name;
        Card commander = restTemplate().getForObject(url, Card.class);

        if (commander.getType_line().startsWith("Legendary Creature")) {
            commander.setCardType(CardType.COMMANDER);
        } else {
            commander.setCardType(CardType.COMMON);
        }
        return commander;
    }

    public List<Card> getCommonCard(int qntdCard, List<String> colors) {

        List<Card> commonCards = new ArrayList<>();
        int page = 1;
        try {
           while (commonCards.size() < qntdCard) {
                String url = String.format(API_URL + API_URL_CARDS, String.join(",", colors)) + page;
                ScryfallResponse response = restTemplate().getForObject(url, ScryfallResponse.class);
                if (response != null && response.getData() != null) {
                    for (Card card : response.getData()) {
                        if (commonCards.size() >= qntdCard) {
                            break;
                        }
                        commonCards.add(card);
                    }
                }
                page++;
            }
            return commonCards;
        } catch (HttpClientErrorException.UnprocessableEntity e) {
          throw  new RuntimeException("cards insuficientes dessa cor para completar o deck");
        }
    }

    public ResponseEntity<Card> getCommander(Card name, Integer qntdCard) {
        Card retorno = this.getCommanderCard(name.getName());

        if (retorno.getCardType().equals(CardType.COMMANDER)) {
            Deck deck = new Deck();
            deck.setCommander(retorno);
            deck.setCards(this.getCommonCard(qntdCard, retorno.getColors()));

            deckService.saveDeck(deck);
            saveCardsToFile(deck, "src/main/resources/deck.json");
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



