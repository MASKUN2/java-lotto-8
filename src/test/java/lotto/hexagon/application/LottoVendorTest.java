package lotto.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.helper.MockRandomGenerator;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoVendorTest {
    private LottoVendor vendor;

    @BeforeEach
    void setUp() {
        LottoFactory mockFactory = MockRandomGenerator::getLotto;
        vendor = new LottoVendor(mockFactory);
    }

    @Test
    @DisplayName("가격이 나눠떨어지지 않아 오류")
    void remainderNotEmpty() {
        Money money = new Money(Lotto.PRICE.value() + 1);
        assertThatIllegalArgumentException().isThrownBy(() -> vendor.purchase(money))
                .withMessage(LottoVendor.ERROR_REMAINDER);
    }

    @Test
    @DisplayName("가격이 나눠떨어져서 그만큼 반환")
    void success() {
        Money money = new Money(Lotto.PRICE.value() * 5);

        Bill bill = vendor.purchase(money);
        Lottos lottos = bill.lottos();

        assertThat(lottos.getSize()).isEqualTo(5);
        assertThat(bill.paid()).isEqualTo(money);

    }

}
