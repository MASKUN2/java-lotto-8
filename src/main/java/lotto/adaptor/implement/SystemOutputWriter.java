package lotto.adaptor.implement;

public class SystemOutputWriter implements ApplicationOutputLineWriter {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
