package lotto.adaptor.implement;

import static lotto.hexagon.domain.Number.MAX_VALUE;
import static lotto.hexagon.domain.Number.MIN_VALUE;
import static lotto.hexagon.domain.Numbers.REQUIRED_SIZE;

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
        List<Integer> integers = Randoms.pickUniqueNumbersInRange(MIN_VALUE, MAX_VALUE, REQUIRED_SIZE);

        return integers.stream()
                .map(Number::new)
                .toList();
    }
}
