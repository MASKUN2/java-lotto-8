package lotto.adaptor.implement;

import static lotto.helper.MockRandomGenerator.LottosBuilder;
import static lotto.helper.MockRandomGenerator.lottoOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.helper.MockLineWriter;
import lotto.hexagon.domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosWriterTest {
    private final MockLineWriter mockLineWriter = new MockLineWriter();
    private final LottosWriter writer = new LottosWriter(mockLineWriter);

    @Test
    @DisplayName("출력테스트")
    void print() {
        LottosBuilder builder = new LottosBuilder();
        builder.add(lottoOf(6, 2, 3, 4, 5, 1));
        builder.add(lottoOf(7, 8, 9, 10, 11, 12));

        Lottos lottos = builder.build();

        writer.write(lottos);

        String out = mockLineWriter.output();
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

        writer.write(lottos);

        String out = mockLineWriter.output();
        String expected = """
                
                0개를 구매했습니다.
                """;

        assertEquals(expected, out);
    }
}
