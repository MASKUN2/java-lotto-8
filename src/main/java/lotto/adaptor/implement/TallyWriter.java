package lotto.adaptor.implement;

import java.util.Locale;
import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Condition;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Prize;
import lotto.hexagon.domain.PrizeTally;

public class TallyWriter {
    private static final String TITLE = "당첨 통계";
    private static final String LINE_SEPARATOR = "---";

    private static final String PRIZE_FORMAT = "%s (%,d원) - %d개";
    private static final String CONDITION_NUMBER_ONLY_FORMAT = "%d개 일치";
    private static final String CONDITION_FORMAT_WITH_BONUS = "%d개 일치, 보너스 볼 일치";

    private static final String RETURN_FORMAT = "총 수익률은 %,.1f%%입니다.";

    private final ApplicationOutputLineWriter writer;

    public TallyWriter(ApplicationOutputLineWriter writer) {
        this.writer = writer;
    }

    public void write(Money paid, Award award) {
        writeLineBreak();
        writeHeadLineSection();
        writePrizeTallySection(award);
        writeReturnSection(paid, award);
    }

    private void writeLineBreak() {
        writer.writeLine("");
    }

    private void writeHeadLineSection() {
        writer.writeLine(TITLE);
        writer.writeLine(LINE_SEPARATOR);
    }

    private void writePrizeTallySection(Award award) {
        for (PrizeTally prizeTally : award) {
            String formatted = formatPrizeTally(prizeTally);
            writer.writeLine(formatted);
        }
    }

    private String formatPrizeTally(PrizeTally prizeTally) {
        Prize prize = prizeTally.prize();

        String condition = formatCondition(prize);
        Money money = prize.money;
        int count = prizeTally.count();

        return String.format(Locale.US, PRIZE_FORMAT, condition, money.value(), count);
    }

    private String formatCondition(Prize prize) {
        Condition condition = prize.condition;
        final int luckyCount = condition.luckyCount();

        if (condition.bonusRequired()) {
            return String.format(CONDITION_FORMAT_WITH_BONUS, luckyCount);
        }
        return String.format(CONDITION_NUMBER_ONLY_FORMAT, luckyCount);
    }

    private void writeReturnSection(Money paid, Award award) {
        Money total = award.total();
        double percentage = (double) total.value() / paid.value() * 100;
        String formattedReturn = String.format(Locale.US, RETURN_FORMAT, percentage);

        writer.writeLine(formattedReturn);
    }

}
