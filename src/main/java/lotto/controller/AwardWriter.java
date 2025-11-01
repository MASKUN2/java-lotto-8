package lotto.controller;

import lotto.hexagon.domain.Award;
import lotto.hexagon.domain.Bill;
import lotto.hexagon.domain.Condition;
import lotto.hexagon.domain.Prize;
import lotto.hexagon.domain.PrizeTally;

public class AwardWriter {

    public void write(Bill bill, Award award) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (PrizeTally prizeTally : award) {
            Prize prize = prizeTally.prize();
            Condition condition = prize.condition;

            int count = prizeTally.count();

            String formatted = String.format("%s개 일치%s (%s원) - %s개",
                    condition.luckyCount(),
                    condition.bonusRequired() ? ", 보너스 볼 일치" : "",
                    String.format("%,d", prize.money.value()),
                    count
            );
            System.out.println(formatted);
        }
        double percentage = ((double) award.total().value() / bill.paid().value()) * 100;
        System.out.printf("총 수익률은 %s입니다.%n", String.format("%.1f%%", percentage));
    }

}
