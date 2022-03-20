package blackjack.controller;

import blackjack.domain.GameStatistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Gamblers gamblers = generatePlayers(cardDeck);
        Dealer dealer = Dealer.of(cardDeck);
        OutputView.printInitGameState(gamblers, dealer);

        playGamblerTurn(gamblers, cardDeck);
        playDealerTurn(dealer, cardDeck);

        OutputView.printCardAndPoint(gamblers, dealer);
        printGameResult(GameStatistic.of(dealer, gamblers), dealer);
    }

    private Gamblers generatePlayers(CardDeck cardDeck) {
        List<Gambler> gamblerList = new ArrayList<>();
        String[] names = InputView.inputGamblerNames();
        for (String name : names) {
            gamblerList.add(generatePlayer(cardDeck, name));
        }
        return Gamblers.of(gamblerList);
    }

    private Gambler generatePlayer(CardDeck cardDeck, String name) {
        Name gamblerName = Name.of(name);
        double money = InputView.inputGamblerBetMoney(gamblerName);
        return Gambler.of(gamblerName, money, cardDeck);
    }

    private void playGamblerTurn(Gamblers gamblers, CardDeck cardDeck) {
        for (Gambler gambler : gamblers.getGamblers()) {
            chooseHitOrStay(cardDeck, gambler);
            printCardStateFirstQuestion(gambler);
        }
    }

    private void chooseHitOrStay(CardDeck cardDeck, Gambler gambler) {
        while (gambler.isHit() && InputView.inputOneMoreCard(gambler.getName())) {
            gambler.addCard(cardDeck.draw());
            OutputView.printPlayerCardState(gambler);
        }
        if (gambler.isHit()) {
            gambler.stay();
        }
    }

    private void printCardStateFirstQuestion(Gambler gambler) {
        if (gambler.isFirstQuestion()) {
            OutputView.printPlayerCardState(gambler);
        }
    }

    private void playDealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHit()) {
            dealer.addCard(cardDeck.draw());
            OutputView.printDealerCardAdded();
        }
        dealer.stay();
    }

    private void printGameResult(GameStatistic gameStatistic, Dealer dealer) {
        OutputView.printGameResult(gameStatistic, dealer);
    }
}
