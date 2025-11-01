package lotto.adaptor.implement;

import lotto.adaptor.OutputWriter;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;

public class OutputWriterImpl implements OutputWriter {
    private final TallyWriter tallyWriter;
    private final LottosWriter lottosWriter;
    private final ApplicationOutputLineWriter lineWriter;

    public OutputWriterImpl(TallyWriter tallyWriter,
                            LottosWriter lottosWriter,
                            ApplicationOutputLineWriter lineWriter) {
        this.tallyWriter = tallyWriter;
        this.lottosWriter = lottosWriter;
        this.lineWriter = lineWriter;
    }

    @Override
    public void write(String message) {
        lineWriter.writeLine(message);
    }

    @Override
    public void write(Lottos lottos) {
        lottosWriter.write(lottos);
    }

    @Override
    public void write(Money paid, Award award) {
        tallyWriter.write(paid, award);
    }
}
