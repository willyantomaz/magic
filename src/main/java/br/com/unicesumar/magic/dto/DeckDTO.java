package br.com.unicesumar.magic.dto;

import br.com.unicesumar.magic.entity.Card;
import lombok.Data;

import java.util.List;

@Data
public class DeckDTO {
    private String user;
    private Card commander;
    private List<Card> cards;


}

