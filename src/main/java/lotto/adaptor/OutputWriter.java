package lotto.adaptor;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;

public interface OutputWriter {
    void write(String message);

    void write(Lottos lottos);

    void write(Money paid, Award award);
}
