package lotto;

import lotto.adaptor.ExceptionHandler;
import lotto.adaptor.InputReader;
import lotto.adaptor.LottoController;
import lotto.adaptor.OutputWriter;
import lotto.adaptor.implement.ApplicationInputLineReader;
import lotto.adaptor.implement.ApplicationOutputLineWriter;
import lotto.adaptor.implement.BillWriter;
import lotto.adaptor.implement.ConsoleInputLineReader;
import lotto.adaptor.implement.InputReaderImpl;
import lotto.adaptor.implement.MoneyReader;
import lotto.adaptor.implement.NumberReader;
import lotto.adaptor.implement.NumbersReader;
import lotto.adaptor.implement.OutputWriterImpl;
import lotto.adaptor.implement.RandomNumbersGenerator;
import lotto.adaptor.implement.RetryableExceptionHandler;
import lotto.adaptor.implement.SystemOutputWriter;
import lotto.adaptor.implement.TallyWriter;
import lotto.hexagon.application.LottoFacadeService;
import lotto.hexagon.application.RewardService;
import lotto.hexagon.application.VendorService;
import lotto.hexagon.domain.NumbersGenerator;
import lotto.hexagon.inbound.LottoOffice;

public class ApplicationConfig {
    public final LottoController lottoController;

    public ApplicationConfig() {
        ExceptionHandler exceptionHandler = getExceptionHandler();
        InputReader reader = getInputReader();
        OutputWriter writer = getOutputWriter();
        LottoOffice lottoOffice = getLottoOffice();

        lottoController = new LottoController(exceptionHandler, reader, writer, lottoOffice);
    }

    private static RetryableExceptionHandler getExceptionHandler() {
        return new RetryableExceptionHandler();
    }

    private InputReader getInputReader() {
        ApplicationInputLineReader inputLineReader = new ConsoleInputLineReader();
        MoneyReader moneyReader = new MoneyReader(inputLineReader);
        NumbersReader numbersReader = new NumbersReader(inputLineReader);
        NumberReader numberReader = new NumberReader(inputLineReader);
        return new InputReaderImpl(moneyReader, numbersReader, numberReader);
    }

    private OutputWriter getOutputWriter() {
        ApplicationOutputLineWriter outputLineWriter = new SystemOutputWriter();
        BillWriter billWriter = new BillWriter();
        TallyWriter tallyWriter = new TallyWriter(outputLineWriter);
        return new OutputWriterImpl(tallyWriter, billWriter, outputLineWriter);
    }

    private LottoOffice getLottoOffice() {
        NumbersGenerator numbersGenerator = new RandomNumbersGenerator();
        VendorService lottoFactory = new VendorService(numbersGenerator);

        RewardService rewardService = new RewardService();

        return new LottoFacadeService(lottoFactory, rewardService);
    }

}
