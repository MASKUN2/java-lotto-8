package lotto;

import lotto.adaptor.RandomNumbersGenerator;
import lotto.controller.AwardWriter;
import lotto.controller.BillWriter;
import lotto.controller.ConsoleLineReader;
import lotto.controller.LineReader;
import lotto.controller.LottoController;
import lotto.controller.MoneyReader;
import lotto.controller.NumberReader;
import lotto.controller.NumbersReader;
import lotto.controller.RetryableExceptionHandler;
import lotto.controller.SystemOutWriter;
import lotto.hexagon.application.AwardService;
import lotto.hexagon.application.LottoFactory;
import lotto.hexagon.application.LottoMaker;
import lotto.hexagon.application.LottoVendor;
import lotto.hexagon.domain.NumbersGenerator;
import lotto.hexagon.inbound.AwardOffice;
import lotto.hexagon.inbound.Vendor;

public class ApplicationConfig {
    public final LottoController lottoController;

    public ApplicationConfig() {
        NumbersGenerator numbersGenerator = new RandomNumbersGenerator();
        LottoFactory lottoFactory = new LottoMaker(numbersGenerator);
        Vendor vendor = new LottoVendor(lottoFactory);

        AwardOffice awardOffice = new AwardService();

        LineReader lineReader = new ConsoleLineReader();
        this.lottoController = new LottoController(
                new MoneyReader(lineReader),
                new SystemOutWriter(),
                new BillWriter(),
                new RetryableExceptionHandler(),
                vendor,
                new NumbersReader(lineReader),
                new NumberReader(lineReader),
                awardOffice,
                new AwardWriter());
    }

}
