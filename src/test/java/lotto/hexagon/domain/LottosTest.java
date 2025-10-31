package lotto.hexagon.domain;

import static lotto.helper.MockRandomGenerator.getLotto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("생성테스트")
    void creation() {

        List<Lotto> lottos = IntStream.range(0, 3)
                .mapToObj(n -> getLotto())
                .toList();

        Lottos lotto = new Lottos(lottos);

        assertThat(lotto.getSize()).isEqualTo(3);

    }

}
