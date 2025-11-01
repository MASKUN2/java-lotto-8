package lotto.adaptor;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.inbound.LottoOffice;

public class LottoController {
    static final String MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    static final String MESSAGE_INPUT_LUCKY_NUMBERS = "당첨 번호를 입력해 주세요.";
    static final String MESSAGE_INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private final InputReader reader;
    private final OutputWriter writer;
    private final RetryExceptionHandler<IllegalArgumentException> exceptionHandler;
    private final LottoOffice lottoOffice;

    public LottoController(
            RetryExceptionHandler<IllegalArgumentException> exceptionHandler,
            InputReader reader,
            OutputWriter writer, LottoOffice lottoOffice) {
        this.exceptionHandler = exceptionHandler;
        this.lottoOffice = lottoOffice;
        this.reader = reader;
        this.writer = writer;
    }

    public void start() {
        Bill bill = doBuying();
        Drawn drawn = drawNumbers();
        Award award = getAward(bill, drawn);
        tally(bill, award);
    }

    private Bill doBuying() {
        writer.write(MESSAGE_INPUT_MONEY);
        Bill bill = exceptionHandler.handle(this::purchase);
        writer.write(bill.lottos());
        return bill;
    }

    private Bill purchase() {
        Money inputMoney = reader.readMoney();
        return lottoOffice.purchase(inputMoney);
    }

    private Drawn drawNumbers() {
        writer.write(MESSAGE_INPUT_LUCKY_NUMBERS);
        Numbers lucky = exceptionHandler.handle(reader::readNumbers);

        writer.write(MESSAGE_INPUT_BONUS_NUMBER);
        Number bonus = exceptionHandler.handle(reader::readNumber);

        return new Drawn(lucky, bonus);
    }

    private Award getAward(Bill bill, Drawn drawn) {
        Lottos lottos = bill.lottos();
        return lottoOffice.determine(drawn, lottos);
    }

    private void tally(Bill bill, Award award) {
        Money paid = bill.paid();
        writer.write(paid, award);
    }

}
