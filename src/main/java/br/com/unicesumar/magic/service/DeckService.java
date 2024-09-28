package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private JwtTokenService jwtTokenService;

    public Deck saveDeck (Deck deck) {
        return this.deckRepository.save(deck);
    }

    @Cacheable("User decks")
    public List<Deck> findDecksByUser (String token) {
        return this.deckRepository.findDeckByUser(this.jwtTokenService.getUserByToken(token.replace("Bearer ", "")));
    }

    @Cacheable("All Decks")
    public List<Deck> findAllDecks () {
        return this.deckRepository.findAll();
    }
}
