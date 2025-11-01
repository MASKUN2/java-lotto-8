package lotto.helper;

import lotto.adaptor.implement.ApplicationInputLineReader;

public class MockLineReader implements ApplicationInputLineReader {
    private String mockLine;

    @Override
    public String readLine() {
        return mockLine;
    }

    public void setLine(String line) {
        this.mockLine = line;
    }
}
