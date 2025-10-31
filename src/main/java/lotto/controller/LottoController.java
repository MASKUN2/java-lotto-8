package lotto.controller;

import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Money;
import lotto.hexagon.inbound.Vendor;

public class LottoController {
    private final InputReader<Money> moneyReader;
    private final OutputWriter<String> messageWriter;
    private final OutputWriter<Bill> purchaseWriter;
    private final ExceptionHandler exceptionHandler;
    private final Vendor vendor;

    public LottoController(InputReader<Money> moneyReader,
                           OutputWriter<String> messageWriter,
                           OutputWriter<Bill> purchaseWriter,
                           ExceptionHandler exceptionHandler, Vendor vendor) {
        this.moneyReader = moneyReader;
        this.messageWriter = messageWriter;
        this.purchaseWriter = purchaseWriter;
        this.exceptionHandler = exceptionHandler;
        this.vendor = vendor;
    }

    public void start() {
        messageWriter.write("구입금액을 입력해 주세요.");
        Bill bill = exceptionHandler.handle(this::purchase);
        purchaseWriter.write(bill);

    }

    private Bill purchase() {
        Money inputMoney = moneyReader.read();
        return vendor.purchase(inputMoney);
    }

}
