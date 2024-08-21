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

    private String type_line;

    private String colors;

    private CardType cardType;


    public void setCardType(){
        if(this.type_line.equals("Legendary Creature")){
            this.cardType = CardType.COMMANDER;
        }else {
            this.cardType = CardType.COMMON;
        }
    }


}
