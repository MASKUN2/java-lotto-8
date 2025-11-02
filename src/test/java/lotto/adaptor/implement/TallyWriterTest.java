package lotto.adaptor.implement;

import static lotto.hexagon.domain.Prize.RANK_5;
import static org.assertj.core.api.Assertions.assertThat;

import lotto.helper.MockLineWriter;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TallyWriterTest {
    private final MockLineWriter lineWriter = new MockLineWriter();
    private final TallyWriter writer = new TallyWriter(lineWriter);

    @Test
    @DisplayName("상금이 있는 상태를 출력")
    void write() {
        Award award = Award.initiate();
        award = award.add(RANK_5);

        writer.write(Money.of(9000), award);

        String output = lineWriter.output();
        String expected = """
                
                당첨 통계
                ---
                3개 일치 (5,000원) - 1개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
                6개 일치 (2,000,000,000원) - 0개
                총 수익률은 55.6%입니다.
                """;

        assertThat(output).isEqualTo(expected);
    }

    @Test
    @DisplayName("상금이 없는 상태를 출력")
    void writeEmpty() {
        Award award = Award.initiate();

        writer.write(Money.of(1000), award);

        String output = lineWriter.output();
        String expected = """
                
                당첨 통계
                ---
                3개 일치 (5,000원) - 0개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
                6개 일치 (2,000,000,000원) - 0개
                총 수익률은 0.0%입니다.
                """;

        assertThat(output).isEqualTo(expected);
    }
}
