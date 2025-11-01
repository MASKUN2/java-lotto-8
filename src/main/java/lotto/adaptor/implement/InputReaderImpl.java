package lotto.adaptor.implement;

import lotto.adaptor.InputReader;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class InputReaderImpl implements InputReader {
    private final MoneyReader moneyReader;
    private final NumbersReader numbersReader;
    private final NumberReader numberReader;

    public InputReaderImpl(MoneyReader moneyReader, NumbersReader numbersReader, NumberReader numberReader) {
        this.moneyReader = moneyReader;
        this.numbersReader = numbersReader;
        this.numberReader = numberReader;
    }

    @Override
    public Money readMoney() throws IllegalArgumentException {
        return moneyReader.read();
    }

    @Override
    public Numbers readNumbers() throws IllegalArgumentException {
        return numbersReader.read();
    }

    @Override
    public Number readNumber() throws IllegalArgumentException {
        return numberReader.read();
    }
}
