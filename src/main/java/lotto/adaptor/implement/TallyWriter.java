package lotto.adaptor.implement;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Condition;
import lotto.hexagon.domain.Money;
import lotto.hexagon.domain.Prize;
import lotto.hexagon.domain.PrizeTally;

public class TallyWriter {
    private static final String TITLE = "당첨 통계";
    private static final String LINE_SEPARATOR = "---";
    private static final String PRIZE_FORMAT = "%s개 일치%s (%s원) - %s개";
    private static final String MONEY_FORMAT = "%,d";
    private static final String RETURN_FORMAT = "총 수익률은 %s입니다.";
    private static final String RETURN_RATE_FORMAT = "%.1f%%";

    private final ApplicationOutputLineWriter writer;

    public TallyWriter(ApplicationOutputLineWriter writer) {
        this.writer = writer;
    }

    public void write(Money paid, Award award) {
        writer.writeLine(TITLE);
        writer.writeLine(LINE_SEPARATOR);
        for (PrizeTally prizeTally : award) {
            Prize prize = prizeTally.prize();
            Condition condition = prize.condition;

            int count = prizeTally.count();

            String formatted = String.format(PRIZE_FORMAT,
                    condition.luckyCount(),
                    getBonusFormat(condition),
                    String.format(MONEY_FORMAT, prize.money.value()),
                    count
            );
            writer.writeLine(formatted);
        }
        double percentage = ((double) award.total().value() / paid.value()) * 100;
        String formattedReturnRate = String.format(RETURN_RATE_FORMAT, percentage);
        String formattedReturn = String.format(RETURN_FORMAT, formattedReturnRate);
        writer.writeLine(formattedReturn);
    }

    private String getBonusFormat(Condition condition) {
        if (condition.bonusRequired()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

}
