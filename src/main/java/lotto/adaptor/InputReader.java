package lotto.adaptor;

import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public interface InputReader {
    Money readMoney() throws IllegalArgumentException;

    Numbers readNumbers() throws IllegalArgumentException;

    Number readNumber() throws IllegalArgumentException;
}
