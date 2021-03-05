package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class UserDeck {

    private static final int ACE_CALIBRATION = 10;
    public static final int BLACK_JACK_NUMBER = 21;
    public static final int BUST_CONDITION = 0;
    private final List<Card> userCards = new ArrayList<>();

    public void add(Card card) {
        userCards.add(card);
    }

    public int deckScore() {
        int score = calculateScore();
        int aceCount = getAceCount();
        while (aceCount != 0 && score > BLACK_JACK_NUMBER) {
            score -= ACE_CALIBRATION;
            aceCount -= 1;
        }
        if (score > BLACK_JACK_NUMBER) {
            return BUST_CONDITION;
        }
        return score;
    }

    private int getAceCount() {
        return (int) userCards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int calculateScore() {
        return userCards.stream()
            .mapToInt(Card::getCardValue)
            .sum();
    }

    public List<Card> getUserCards() {
        return userCards;
    }
}