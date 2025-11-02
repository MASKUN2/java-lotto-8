package lotto;

import lotto.adaptor.InputReader;
import lotto.adaptor.LottoController;
import lotto.adaptor.OutputWriter;
import lotto.adaptor.RetryableExceptionHandler;
import lotto.adaptor.implement.ApplicationInputLineReader;
import lotto.adaptor.implement.ApplicationOutputLineWriter;
import lotto.adaptor.implement.ConsoleInputLineReader;
import lotto.adaptor.implement.LottoOutputWriter;
import lotto.adaptor.implement.LottosWriter;
import lotto.adaptor.implement.RandomNumbersGenerator;
import lotto.adaptor.implement.RetryableExceptionHandlerImpl;
import lotto.adaptor.implement.SystemOutputLineWriter;
import lotto.adaptor.implement.TallyWriter;
import lotto.adaptor.implement.UserInputReader;
import lotto.hexagon.application.LottoFacadeService;
import lotto.hexagon.application.RewardService;
import lotto.hexagon.application.VendorService;
import lotto.hexagon.domain.NumbersGenerator;
import lotto.hexagon.inbound.LottoOffice;

public class ApplicationConfig {
    private final ApplicationInputLineReader inputLineReader;
    private final ApplicationOutputLineWriter outputLineWriter;

    private final LottoController lottoController;

    public ApplicationConfig() {
        inputLineReader = getInputLineReader();
        outputLineWriter = getOutputLineWriter();

        RetryableExceptionHandler<IllegalArgumentException> exceptionHandler = getExceptionHandler();
        InputReader reader = getInputReader();
        OutputWriter writer = getOutputWriter();
        LottoOffice lottoOffice = getLottoOffice();

        lottoController = new LottoController(exceptionHandler, reader, writer, lottoOffice);
    }

    private ApplicationInputLineReader getInputLineReader() {
        return new ConsoleInputLineReader();
    }

    private ApplicationOutputLineWriter getOutputLineWriter() {
        return new SystemOutputLineWriter();
    }

    public LottoController getLottoController() {
        return lottoController;
    }

    private RetryableExceptionHandler<IllegalArgumentException> getExceptionHandler() {
        return new RetryableExceptionHandlerImpl(outputLineWriter);
    }

    private InputReader getInputReader() {
        return new UserInputReader(inputLineReader);
    }

    private OutputWriter getOutputWriter() {
        LottosWriter lottosWriter = new LottosWriter(outputLineWriter);
        TallyWriter tallyWriter = new TallyWriter(outputLineWriter);

        return new LottoOutputWriter(tallyWriter, lottosWriter, outputLineWriter);
    }

    private LottoOffice getLottoOffice() {
        NumbersGenerator numbersGenerator = new RandomNumbersGenerator();
        VendorService lottoFactory = new VendorService(numbersGenerator);

        RewardService rewardService = new RewardService();

        return new LottoFacadeService(lottoFactory, rewardService);
    }

}
