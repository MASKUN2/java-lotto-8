package lotto.controller;

import static lotto.helper.MockRandomGenerator.LottosBuilder;
import static lotto.helper.MockRandomGenerator.lottoOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.helper.SystemIoTestHelper;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BillWriterTest {
    private final BillWriter writer = new BillWriter();

    @BeforeEach
    void setUp() {
        SystemIoTestHelper.startRecord();
    }

    @AfterEach
    void tearDown() {
        SystemIoTestHelper.restore();
    }

    @Test
    @DisplayName("출력테스트")
    void print() {
        LottosBuilder builder = new LottosBuilder();
        builder.add(lottoOf(6, 2, 3, 4, 5, 1));
        builder.add(lottoOf(7, 8, 9, 10, 11, 12));

        Lottos lottos = builder.build();
        Money paid = new Money(2000);
        Bill bill = new Bill(paid, lottos);

        writer.write(bill);

        String out = SystemIoTestHelper.output();
        String expected = """
                
                2개를 구매했습니다.
                [1, 2, 3, 4, 5, 6]
                [7, 8, 9, 10, 11, 12]
                """;

        assertEquals(expected, out);
    }

    @Test
    @DisplayName("빈 출력테스트")
    void printEmpty() {
        LottosBuilder builder = new LottosBuilder();

        Lottos lottos = builder.build();
        Money paid = new Money(0);
        Bill bill = new Bill(paid, lottos);

        writer.write(bill);

        String out = SystemIoTestHelper.output();
        String expected = """
                
                0개를 구매했습니다.
                """;

        assertEquals(expected, out);
    }
}
