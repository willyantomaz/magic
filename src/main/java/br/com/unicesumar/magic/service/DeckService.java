package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    public Deck saveDeck (Deck deck) {
        return this.deckRepository.save(deck);
    }

    public List<Deck> findAllDecks () {
        return this.deckRepository.findAll();
    }
}
