package lotto.helper;

import lotto.adaptor.implement.ApplicationOutputLineWriter;

public class MockLineWriter implements ApplicationOutputLineWriter {
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void writeLine(String line) {
        stringBuilder.append(line);
        stringBuilder.append("\n");
    }

    public void reset() {
        stringBuilder.setLength(0);
    }

    public String output() {
        return stringBuilder.toString();
    }
}
