package lotto.controller;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.inbound.AwardOffice;
import lotto.hexagon.inbound.Vendor;

public class LottoController {
    private final InputReader<Money> moneyReader;
    private final OutputWriter<String> messageWriter;
    private final OutputWriter<Bill> purchaseWriter;
    private final ExceptionHandler exceptionHandler;
    private final Vendor vendor;
    private final InputReader<Numbers> numberReader;
    private final InputReader<Number> numberWriter;
    private final AwardOffice awardOffice;
    private final AwardWriter awardWriter;

    public LottoController(InputReader<Money> moneyReader,
                           OutputWriter<String> messageWriter,
                           OutputWriter<Bill> purchaseWriter,
                           ExceptionHandler exceptionHandler, Vendor vendor, InputReader<Numbers> numberReader,
                           InputReader<Number> numberWriter, AwardOffice awardOffice, AwardWriter awardWriter) {
        this.moneyReader = moneyReader;
        this.messageWriter = messageWriter;
        this.purchaseWriter = purchaseWriter;
        this.exceptionHandler = exceptionHandler;
        this.vendor = vendor;
        this.numberReader = numberReader;
        this.numberWriter = numberWriter;
        this.awardOffice = awardOffice;
        this.awardWriter = awardWriter;
    }

    public void start() {
        messageWriter.write("구입금액을 입력해 주세요.");
        Bill bill = exceptionHandler.handle(this::purchase);
        purchaseWriter.write(bill);

        messageWriter.write("당첨 번호를 입력해 주세요.");
        Numbers lucky = exceptionHandler.handle(numberReader::read);

        messageWriter.write("보너스 번호를 입력해 주세요.");
        Number bonus = exceptionHandler.handle(numberWriter::read);

        Drawn drawn = new Drawn(lucky, bonus);

        Award award = awardOffice.determine(drawn, bill.lottos());
        awardWriter.write(bill, award);

    }

    private Bill purchase() {
        Money inputMoney = moneyReader.read();
        return vendor.purchase(inputMoney);
    }

}
