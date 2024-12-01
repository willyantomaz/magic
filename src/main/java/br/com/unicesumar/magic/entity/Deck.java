package br.com.unicesumar.magic.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "deck")
public class Deck {

    @Id
    private ObjectId id;

    private String user;

    private Card commander;

    private List<Card> cards;
}
