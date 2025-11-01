package lotto.hexagon.application;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Drawn;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import lotto.hexagon.inbound.LottoOffice;

public class LottoFacadeService implements LottoOffice {
    private final VendorService vendorService;
    private final RewardService rewardService;

    public LottoFacadeService(VendorService vendorService, RewardService rewardService) {
        this.vendorService = vendorService;
        this.rewardService = rewardService;
    }

    @Override
    public Bill purchase(Money money) throws IllegalArgumentException {
        return vendorService.purchase(money);
    }

    @Override
    public Award determine(Drawn drawn, Lottos lottos) {
        return rewardService.determine(drawn, lottos);
    }
}
