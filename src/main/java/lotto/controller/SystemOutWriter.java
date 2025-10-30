package lotto.controller;

public class SystemOutWriter implements OutputWriter<String> {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
