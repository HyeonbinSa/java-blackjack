package blackjack.domain.human;

import java.util.regex.Pattern;

public class Name {

    public static final String NAME_ERROR_MESSAGE = "[ERROR] 입력 형식에 맞춰 입력해주세요.";
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]+");

    private final String name;

    private Name(String name) {
        this.name = name;
        validateName(name);
    }

    private static void validateName(String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new RuntimeException(NAME_ERROR_MESSAGE);
        }
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
