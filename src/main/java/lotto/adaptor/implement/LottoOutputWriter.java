package lotto.adaptor.implement;

import lotto.adaptor.OutputWriter;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;

public class LottoOutputWriter implements OutputWriter {
    private static final String MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_INPUT_LUCKY_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private final TallyWriter tallyWriter;
    private final LottosWriter lottosWriter;
    private final ApplicationOutputLineWriter lineWriter;

    public LottoOutputWriter(TallyWriter tallyWriter,
                             LottosWriter lottosWriter,
                             ApplicationOutputLineWriter lineWriter) {
        this.tallyWriter = tallyWriter;
        this.lottosWriter = lottosWriter;
        this.lineWriter = lineWriter;
    }

    @Override
    public void writeRequestInputMoney() {
        lineWriter.writeLine(MESSAGE_INPUT_MONEY);
    }

    @Override
    public void writeRequestInputLuckyNumbers() {
        lineWriter.writeLine(MESSAGE_INPUT_LUCKY_NUMBERS);

    }

    @Override
    public void writeRequestInputBonusNumber() {
        lineWriter.writeLine(MESSAGE_INPUT_BONUS_NUMBER);
    }

    @Override
    public void writeDetail(Lottos lottos) {
        lottosWriter.write(lottos);
    }

    @Override
    public void writeResult(Money paid, Award award) {
        tallyWriter.write(paid, award);
    }
}
