package lotto.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.helper.MockRandomGenerator;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.NumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMakerTest {
    private LottoMaker maker;

    @BeforeEach
    void setUp() {
        NumbersGenerator mockGenerator = MockRandomGenerator::getNumbers;
        maker = new LottoMaker(mockGenerator);
    }

    @Test
    @DisplayName("생성테스트")
    void creation() {

        Lotto lotto = maker.create();

        assertThat(lotto).isNotNull();
        assertThat(lotto.getNumbers()).isNotNull();
    }
}
