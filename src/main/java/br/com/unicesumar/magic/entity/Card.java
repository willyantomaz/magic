package br.com.unicesumar.magic.entity;


import br.com.unicesumar.magic.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Document(collection = "card")
public class Card {

    @Id
    private String id;

    private String name;

    private CardType cardType;

    private Legalities legalities;

    public void setCardType(){
        if(legalities.getCommander().equals("legal")){
            this.cardType = CardType.COMMANDER;
        }else {
            this.cardType = CardType.COMMON;
        }
    }


}
