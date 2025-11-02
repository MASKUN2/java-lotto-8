package lotto.adaptor;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;

public interface OutputWriter {
    void writeRequestInputMoney();

    void writeRequestInputLuckyNumbers();

    void writeRequestInputBonusNumber();

    void writeDetail(Lottos lottos);

    void writeResult(Money paid, Award award);
}
