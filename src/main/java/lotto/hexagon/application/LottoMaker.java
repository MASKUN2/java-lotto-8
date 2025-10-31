package lotto.hexagon.application;

import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.domain.NumbersGenerator;

public class LottoMaker implements LottoFactory {
    private final NumbersGenerator numbersGenerator;

    public LottoMaker(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    @Override
    public Lotto create() {
        Numbers numbers = numbersGenerator.generate();
        return new Lotto(numbers);
    }
}
