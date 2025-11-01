package lotto;

import lotto.adaptor.LottoController;

public class Application {
    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig();
        LottoController lottoController = config.getLottoController();
        lottoController.start();
    }
}
