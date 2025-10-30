package lotto.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SystemIoTestHelper {
    private static final String lineBreak = "\n";

    private static final InputStream STANDARD_IN = System.in;
    private static final PrintStream STANDARD_OUT = System.out;
    private static final ByteArrayOutputStream CAPTOR = new ByteArrayOutputStream();

    private SystemIoTestHelper() {
    }

    public static void setInput(String... input) {
        String combined = String.join(lineBreak, input) + lineBreak;
        ByteArrayInputStream in = new ByteArrayInputStream(combined.getBytes());
        System.setIn(in);
    }

    public static void startRecord() {
        CAPTOR.reset();
        System.setOut(new PrintStream(CAPTOR));
    }

    public static void restore() {
        System.setIn(STANDARD_IN);
        System.setOut(STANDARD_OUT);
    }

    public static String output() {
        return CAPTOR.toString();
    }
}
