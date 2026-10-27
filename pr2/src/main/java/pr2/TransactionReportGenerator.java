package pr2;

import java.util.List;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + " : " + expense.getAmount());
        }
    }

    public void printVisualExpenseReport(List<Transaction> transactions) {
        System.out.println("\nВізуалізація витрат за категоріями (* = 1000 грн):");

        java.util.Map<String, Double> categoryTotals = new java.util.HashMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                categoryTotals.put(
                        t.getDescription(),
                        categoryTotals.getOrDefault(t.getDescription(), 0.0) + Math.abs(t.getAmount())
                );
            }
        }

        for (java.util.Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            int starsCount = (int) (entry.getValue() / 1000);
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.println(entry.getKey() + " (" + entry.getValue() + " грн): " + stars);
        }
    }
}
