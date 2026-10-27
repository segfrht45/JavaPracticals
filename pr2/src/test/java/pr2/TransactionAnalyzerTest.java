package pr2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {

        Transaction t1 = new Transaction("01-01-2024", 100.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2024", -50.0, "Витрата");
        List<Transaction> transactions = Arrays.asList(t1, t2);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        double result = analyzer.calculateTotalBalance();

        Assertions.assertEquals(50.0, result, "Неправильний розрахунок загального балансу");
    }
}