package lotto.adaptor.implement;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputLineReader implements ApplicationInputLineReader {

    @Override
    public String readLine() {
        return Console.readLine();
    }
}
