package lotto.adaptor;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.inbound.LottoOffice;

public class LottoController {
    private final RetryableExceptionHandler<IllegalArgumentException> exceptionHandler;
    private final InputReader reader;
    private final OutputWriter writer;
    private final LottoOffice lottoOffice;

    public LottoController(
            RetryableExceptionHandler<IllegalArgumentException> exceptionHandler,
            InputReader reader,
            OutputWriter writer,
            LottoOffice lottoOffice) {
        this.exceptionHandler = exceptionHandler;
        this.lottoOffice = lottoOffice;
        this.reader = reader;
        this.writer = writer;
    }

    public void start() {
        Bill bill = exceptionHandler.handle(this::purchase);
        writer.writeDetail(bill.lottos());

        Drawn drawn = exceptionHandler.handle(this::draw);

        Award award = lottoOffice.determine(drawn, bill.lottos());

        writer.writeResult(bill.paid(), award);
    }

    private Bill purchase() {
        writer.writeRequestInputMoney();
        Money inputMoney = reader.readMoney();

        return lottoOffice.purchase(inputMoney);
    }

    private Drawn draw() {
        writer.writeRequestInputLuckyNumbers();
        Numbers lucky = reader.readNumbers();

        writer.writeRequestInputBonusNumber();
        Number bonus = reader.readNumber();

        return new Drawn(lucky, bonus);
    }

}
