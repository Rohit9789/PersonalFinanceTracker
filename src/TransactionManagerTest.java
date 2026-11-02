public class TransactionManagerTest {

    public static void main(String[] args) {

        TransactionManager manager = new TransactionManager();

Transaction transaction = new Transaction(
    100, "Income", "Salary", 25000,
    "Test salary", "02-11-2026"
);

manager.addTransaction(transaction);

Transaction expense = new Transaction(
    101, "Expense", "Food", 350,
    "Test lunch", "02-11-2026"
);

manager.addTransaction(expense);

Transaction duplicate = new Transaction(
    100, "Expense", "Food", 500,
    "Duplicate test", "02-11-2026"
);

manager.addTransaction(duplicate);

assert manager.getTotalExpenses() == 350 :
        "Duplicate ID test failed!";

assert manager.getTotalIncome() == 25000 :
        "Total income test failed!";

assert manager.getTotalExpenses() == 350 :
        "Total expenses test failed!";

assert manager.getBalance() == 24650 :
        "Balance test failed!";

System.out.println("Valid transaction test completed!");

manager.viewTransactions();

    }
}