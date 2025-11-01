package lotto.controller;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleLineReader implements LineReader {

    @Override
    public Line read() {
        return new Line(readLine());
    }

    private String readLine() {
            return Console.readLine();
    }
}
