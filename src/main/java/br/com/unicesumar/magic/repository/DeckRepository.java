package br.com.unicesumar.magic.repository;

import br.com.unicesumar.magic.entity.Deck;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeckRepository extends MongoRepository<Deck,String> {
}
