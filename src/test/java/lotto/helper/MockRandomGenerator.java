package lotto.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.hexagon.domain.Lotto;
import lotto.hexagon.domain.Lottos;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;

public class MockRandomGenerator {
    private MockRandomGenerator() {
    }

    public static List<Integer> getIntegers() {
        List<Integer> integers = IntStream.rangeClosed(Number.MIN_VALUE, Number.MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(integers);

        return integers.stream()
                .limit(Numbers.REQUIRED_SIZE)
                .toList();
    }

    public static List<Number> getNumberList() {
        List<Integer> integers = getIntegers();
        return integers.stream()
                .map(Number::new)
                .toList();
    }

    public static Numbers getNumbers() {
        return Numbers.of(getNumberList());
    }

    public static Lotto getLotto() {
        return new Lotto(getNumbers());
    }

    public static Numbers numbersOf(int... ints) {
        List<Number> list = Arrays.stream(ints)
                .mapToObj(Number::new)
                .toList();

        return Numbers.of(list);
    }

    public static Lotto lottoOf(int... ints) {
        List<Number> list = Arrays.stream(ints)
                .mapToObj(Number::new)
                .toList();

        Numbers numbers = Numbers.of(list);
        return new Lotto(numbers);
    }

    public static class LottosBuilder {
        private final List<Lotto> lottos = new ArrayList<>();

        public LottosBuilder() {
        }

        public LottosBuilder add(Lotto lotto) {
            lottos.add(lotto);
            return this;
        }

        public Lottos build() {
            return new Lottos(lottos);
        }
    }
}
