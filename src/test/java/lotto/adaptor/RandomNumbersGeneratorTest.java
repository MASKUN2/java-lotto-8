package lotto.adaptor;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {

    @Test
    @DisplayName("랜덤번호생성")
    void generate() {
        RandomNumbersGenerator generator = new RandomNumbersGenerator();

        assertThatCode(generator::generate).doesNotThrowAnyException();
    }

}
