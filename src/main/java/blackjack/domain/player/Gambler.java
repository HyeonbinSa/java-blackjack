package blackjack.domain.player;

public class Gambler extends Player {

    private Gambler(Name name) {
        super(name);
    }

    public static Gambler of(Name name) {
        return new Gambler(name);
    }

    @Override
    public boolean isHit() {
        return !isBust();
    }
}
