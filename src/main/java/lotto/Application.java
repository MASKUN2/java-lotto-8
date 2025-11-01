package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig();
        LottoController lottoController = config.lottoController;
        lottoController.start();
    }
}
