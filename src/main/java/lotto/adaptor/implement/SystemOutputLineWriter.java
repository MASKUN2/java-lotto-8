package lotto.adaptor.implement;

public class SystemOutputLineWriter implements ApplicationOutputLineWriter {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
