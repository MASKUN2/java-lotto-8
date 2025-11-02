package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ApplicationAcceptanceTest extends NsTest {
    private static final String ERROR_PREFIX = "[ERROR]";

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @ParameterizedTest
    @MethodSource("provideSuccessCases")
    void success(Pair pair) {

        successWith(
                pair.input,
                pair.expectedOutput,
                pair.getFistNumbers(),
                pair.getRestNumbers()
        );
    }

    private static Stream<Pair> provideSuccessCases() {
        return Stream.of(
                new Pair(of("1000", "1,2,3,4,5,6", "7"), of("6개 일치 (2,000,000,000원) - 1개"),
                        "1등", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,45", "6"), of("5개 일치, 보너스 볼 일치 (30,000,000원) - 1개"),
                        "2등", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,45", "44"), of("5개 일치 (1,500,000원) - 1개"),
                        "3등", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,43,45", "44"), of("4개 일치 (50,000원) - 1개"),
                        "4등", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,42,43,45", "44"), of("3개 일치 (5,000원) - 1개"),
                        "5등", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,41,42,43,45", "44"), of("총 수익률은 0.0%입니다."),
                        "꽝", of(1, 2, 3, 4, 5, 6)),
                new Pair(of("3000", "1,2,3,4,5,6", "7"), of("3개 일치 (5,000원) - 1개", "4개 일치 (50,000원) - 1개",
                        "총 수익률은 1833.3%입니다."), "5등, 4등",
                        of(1, 41, 3, 4, 9, 6, 7, 8, 1, 10, 11, 9, 13, 9, 6, 4, 1, 37))
        );
    }

    @ParameterizedTest
    @MethodSource("provideExceptionCases")
    @DisplayName("예외테스트")
    void exception(Pair pair) {
        errorWith(pair.input, pair.expectedOutput);
    }

    private void successWith(List<String> input, List<String> expectedOutput, List<Integer> fixedNumber,
                             List<Integer>[] fixedNumbers) {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run(input.toArray(String[]::new));
                    assertThat(output()).contains(expectedOutput);
                },
                fixedNumber,
                fixedNumbers
        );
    }

    private static Stream<Pair> provideExceptionCases() {
        return Stream.of(
                new Pair(of("A"), of(ERROR_PREFIX), "문자금액", List.of()),
                new Pair(of("0"), of(ERROR_PREFIX), "0원", List.of()),
                new Pair(of("1001"), of(ERROR_PREFIX), "나누어떨지지지 않음", List.of()),
                new Pair(of("1000", "1,2,3,4,5"), of(ERROR_PREFIX), "당첨번호 부족",
                        of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,46"), of(ERROR_PREFIX), "번호 범위 초과",
                        of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,A"), of(ERROR_PREFIX), "번호 아님",
                        of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,6", "1"), of(ERROR_PREFIX), "중복번호",
                        of(1, 2, 3, 4, 5, 6)),
                new Pair(of("1000", "1,2,3,4,5,6", "46"), of(ERROR_PREFIX), "보너스 번호범위초과",
                        of(1, 2, 3, 4, 5, 6))
        );
    }

    private void errorWith(List<String> input, List<String> expectedOutput) {
        assertSimpleTest(() -> {
            runException(input.toArray(String[]::new));
            assertThat(output()).contains(expectedOutput);
        });
    }

    record Pair(List<String> input, List<String> expectedOutput, String reason, List<Integer> fixedNumbers) {
        public List<Integer> getFistNumbers() {
            return fixedNumbers.subList(0, 6);
        }

        public List<Integer>[] getRestNumbers() {
            List<List<Integer>> restNumbers = new ArrayList<>();
            for (int i = 6; i < fixedNumbers.size(); i += 6) {
                List<Integer> subList = fixedNumbers.subList(i, i + 6);
                restNumbers.add(subList);
            }
            return restNumbers.toArray(List[]::new);
        }
    }

}
