package lotto.controller;

import static lotto.hexagon.domain.Prize.RANK_5;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.helper.SystemIoTestHelper;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AwardWriterTest {
    private final AwardWriter writer = new AwardWriter();
    private Bill bill;
    private Award award;

    @BeforeEach
    void setUp() {
        SystemIoTestHelper.startRecord();

        bill = new Bill(Money.of(9000), new Lottos(List.of()));
        award = Award.initiate();
        award = award.add(RANK_5);
    }

    @AfterEach
    void tearDown() {
        SystemIoTestHelper.restore();
    }

    @Test
    void write() {
        writer.write(bill, award);

        String output = SystemIoTestHelper.output();
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
}
