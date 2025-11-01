package lotto.adaptor.implement;

import lotto.adaptor.OutputWriter;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Money;

public class OutputWriterImpl implements OutputWriter {
    private final TallyWriter tallyWriter;
    private final BillWriter billWriter;
    private final ApplicationOutputLineWriter lineWriter;

    public OutputWriterImpl(TallyWriter tallyWriter, BillWriter billWriter, ApplicationOutputLineWriter lineWriter) {
        this.tallyWriter = tallyWriter;
        this.billWriter = billWriter;
        this.lineWriter = lineWriter;
    }

    @Override
    public void write(String message) {
        lineWriter.writeLine(message);
    }

    @Override
    public void write(Bill bill) {
        billWriter.write(bill);
    }

    @Override
    public void write(Money paid, Award award) {
        tallyWriter.write(paid, award);
    }
}
