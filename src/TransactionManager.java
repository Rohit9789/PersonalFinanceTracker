import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

public class TransactionManager {

    private ArrayList<Transaction> transactions;
    private FileHandler fileHandler;
    public TransactionManager() {
        transactions = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Invalid transaction!");
            return;
        }
        if (transaction.getAmount() <= 0 || !Double.isFinite(transaction.getAmount())) {
            System.out.println("Invalid transaction amount!");
            return;
        }
            if (!transaction.getType().equals("Income") &&
                    !transaction.getType().equals("Expense")) {
                System.out.println("Invalid transaction type!");
                return;
        }
        if (transaction.getCategory() == null ||
                transaction.getCategory().trim().isEmpty()) {
            System.out.println("Invalid transaction category!");
            return;
        }
        if (transaction.getDescription() == null ||
                transaction.getDescription().trim().isEmpty()) {
            System.out.println("Invalid transaction description!");
            return;
        }
        if (transaction.getDate() == null) {
            System.out.println("Invalid transaction date!");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(transaction.getDate(), formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid transaction date!");
            return;
        }
        
        if (transaction.getId() <= 0) {
            System.out.println("Invalid transaction ID!");
            return;
        }

        for (Transaction existing : transactions) {
            if (existing.getId() == transaction.getId()) {
                System.out.println("Duplicate transaction ID!");
                return;
            }
        }
              
        transactions.add(transaction);
    }

        public int getNextId() {

            int maxId = 0;

            for (Transaction transaction : transactions) {

                if (transaction.getId() > maxId) {
                    maxId = transaction.getId();
                }
            }

            return maxId + 1;

    }
    public void viewTransactions() {

        for (Transaction transaction : transactions) {

            System.out.println("ID: " + transaction.getId());
            System.out.println("Type: " + transaction.getType());
            System.out.println("Category: " + transaction.getCategory());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Description: " + transaction.getDescription());
            System.out.println("Date: " + transaction.getDate());

            System.out.println("-------------------------");
        }
    }

                public double getTotalIncome() {


                double total = 0;

                for (Transaction transaction : transactions) {

                    if (transaction.getType().equals("Income")) {
                        total = total + transaction.getAmount();
                    }
                }

                return total;
            }
                public double getTotalExpenses() {

            double total = 0;

            for (Transaction transaction : transactions) {

                if (transaction.getType().equals("Expense")) {
                    total = total + transaction.getAmount();
                }
            }

            return total;
        }
                public double getBalance() {

            return getTotalIncome() - getTotalExpenses();

        }
                public void searchTransaction(int id) {

            boolean found = false;

            for (Transaction transaction : transactions) {

                if (transaction.getId() == id) {

                    System.out.println("Transaction Found!");
                    System.out.println("ID: " + transaction.getId());
                    System.out.println("Type: " + transaction.getType());
                    System.out.println("Category: " + transaction.getCategory());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println("Description: " + transaction.getDescription());
                    System.out.println("Date: " + transaction.getDate());

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Transaction not found!");
            }
        }
                public void deleteTransaction(int id) {

            for (Transaction transaction : transactions) {

                if (transaction.getId() == id) {

                    transactions.remove(transaction);

                    System.out.println("Transaction deleted successfully!");
                    return;
                }
            }

            System.out.println("Transaction not found!");
        }
            public void saveTransactions() {
                fileHandler.saveTransactions(transactions);
            }
            public void loadTransactions() {
                transactions = fileHandler.loadTransactions();
            }
}
