package lotto.adaptor.implement;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.hexagon.domain.Number;
import lotto.hexagon.domain.Numbers;
import lotto.hexagon.domain.NumbersGenerator;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public Numbers generate() {
        List<Number> list = getRandomUniques();
        return Numbers.of(list);
    }

    private static List<Number> getRandomUniques() {
        int min = Number.MIN_VALUE;
        int max = Number.MAX_VALUE;
        int size = Numbers.REQUIRED_SIZE;

        List<Integer> integers = Randoms.pickUniqueNumbersInRange(min, max, size);

        return integers.stream()
                .map(Number::new)
                .toList();
    }
}
