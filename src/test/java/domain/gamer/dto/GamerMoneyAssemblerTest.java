package domain.gamer.dto;

import domain.card.*;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.GameResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GamerMoneyAssemblerTest {
    private GamerMoneyDto gamerMoneyDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerMoneyDto = GamerMoneyAssembler.create(new Player(playingCards, "testName"), 1500);
    }

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThat(GamerMoneyAssembler.create(new Player(new PlayingCards(Collections.emptyList()), "testName"), 1500)).isNotNull();
    }

    @Test
    @DisplayName("List Dto 생성 테스트")
    void createDtos() {
        Deck deck = DeckFactory.create();
        Dealer dealer = new Dealer(deck.dealInitCards());
        Players players = Players.valueOf(deck, Arrays.asList(new GamerMoneyDto("a", 0),
                new GamerMoneyDto("b", 0),
                new GamerMoneyDto("c", 0)));
        GameResults gameResults = new GameResults(dealer, players);
        assertThat(GamerMoneyAssembler.createDtos(dealer, players, gameResults)).isNotNull();
    }
}