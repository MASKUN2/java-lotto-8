package lotto.helper;

import lotto.controller.Line;
import lotto.controller.LineReader;

public class MockLineReader implements LineReader {
    private Line mockLine;

    @Override
    public Line read() {
        return mockLine;
    }

    public void setLine(Line line) {
        this.mockLine = line;
    }
}
