package lotto.adaptor;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Money;

public interface OutputWriter {
    void write(String message);

    void write(Bill bill);

    void write(Money paid, Award award);
}
