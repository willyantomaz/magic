package br.com.unicesumar.magic.service;

import br.com.unicesumar.magic.dto.DeckDTO;
import br.com.unicesumar.magic.entity.Card;
import br.com.unicesumar.magic.entity.Deck;
import br.com.unicesumar.magic.entity.Usuario;
import br.com.unicesumar.magic.repository.DeckRepository;
import br.com.unicesumar.magic.repository.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public void validateAndImportDeck(DeckDTO deckDTO) {

        if (!isValidCommander(deckDTO.getCommander())) {
            throw new RuntimeException("Comandante inválido.");
        }


        if (!areColorsConsistent(deckDTO.getCommander().getColors(), deckDTO.getCards())) {
            throw new RuntimeException("As cores das cartas não são consistentes com o comandante.");
        }


        Usuario user = usuarioRepository.findUserByLogin(deckDTO.getUser())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));


        Deck deck = new Deck();
        deck.setCommander(deckDTO.getCommander());
        deck.setCards(new ArrayList<>(deckDTO.getCards()));
        deck.setUser(String.valueOf(user));

        deckRepository.save(deck);
    }

    private boolean isValidCommander(Card commander) {

        return true;

    }

    private boolean areColorsConsistent(List<String> commanderColors, List<Card> cards) {

        return cards.stream()
                .allMatch(card -> commanderColors.containsAll(card.getColors()));
    }
}



