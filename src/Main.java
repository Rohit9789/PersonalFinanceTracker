import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.ResolverStyle;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TransactionManager manager = new TransactionManager();

        manager.loadTransactions();

        int choice;

        while (true) {
            System.out.println("\n===== Personal Finance Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Total Income");
            System.out.println("5. View Total Expenses");
            System.out.println("6. View Available Balance");
            System.out.println("7. Search Transaction");
            System.out.println("8. Delete Transaction");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            String menuInput = scanner.nextLine();

            try {
                choice = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu number!");
                continue;
            }

            if (choice < 1 || choice > 9) {
                System.out.println("Please enter a choice between 1 and 9!");
                continue;
            }

            switch (choice) {

                case 1: {
                    System.out.print("Enter income category: ");
                    String category = scanner.nextLine().trim();

                    if (category.isEmpty()) {
                        System.out.println("Category cannot be empty!");
                        break;
                    }

                    System.out.print("Enter amount: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Please enter a valid number!");
                        scanner.nextLine();
                        break;
                    }

                    double amount = scanner.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than zero!");
                        scanner.nextLine();
                        break;
                    }

                    System.out.print("Enter description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine().trim();

                    if (description.isEmpty()) {
                        System.out.println("Description cannot be empty!");
                        break;
                    }

                    System.out.print("Enter date (DD-MM-YYYY): ");
                    String date = scanner.nextLine().trim();

                    DateTimeFormatter formatter = DateTimeFormatter
                            .ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT);

                    try {
                        LocalDate.parse(date, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please enter a valid date!");
                        break;
                    }

                    int id = manager.getNextId();

                    Transaction income = new Transaction(
                        id, "Income", category, amount, description, date
                    );

                    manager.addTransaction(income);

                    System.out.println("Income added successfully!");

                    break;
                }

                case 2: {
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine().trim();

                    if (category.isEmpty()) {
                        System.out.println("Category cannot be empty!");
                        break;
                    }

                    System.out.print("Enter amount: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Please enter a valid number!");
                        scanner.nextLine();
                        break;
                    }

                    double amount = scanner.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than zero!");
                        scanner.nextLine();
                        break;
                    }

                    System.out.print("Enter description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine().trim();

                    if (description.isEmpty()) {
                        System.out.println("Description cannot be empty!");
                        break;
                    }

                    System.out.print("Enter date (DD-MM-YYYY): ");
                    String date = scanner.nextLine().trim();

                    DateTimeFormatter formatter = DateTimeFormatter
                            .ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT);

                    try {
                        LocalDate.parse(date, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please enter a valid date!");
                        break;
                    }

                    int id = manager.getNextId();

                    Transaction expense = new Transaction(
                        id, "Expense", category, amount, description, date
                    );

                    manager.addTransaction(expense);

                    System.out.println("Expense added successfully!");

                    break;
                }

                case 3:
                    manager.viewTransactions();
                    break;

                case 4:
                    System.out.println("Total Income: " + manager.getTotalIncome());
                    break;

                case 5:
                    System.out.println("Total Expenses: " + manager.getTotalExpenses());
                    break;

                case 6:
                    System.out.println("Available Balance: " + manager.getBalance());
                    break;

                case 7: {
                    System.out.print("Enter Transaction ID: ");

                    String searchInput = scanner.nextLine();
                    int searchId;

                    try {
                        searchId = Integer.parseInt(searchInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid transaction ID!");
                        break;
                    }
                    if (searchId <= 0) {
                        System.out.println("Transaction ID must be greater than zero!");
                        break;
                    }

                    manager.searchTransaction(searchId);
                    break;
                }

                case 8: {
                    System.out.print("Enter Transaction ID to delete: ");

                    String deleteInput = scanner.nextLine();
                    int deleteId;

                    try {
                        deleteId = Integer.parseInt(deleteInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid transaction ID!");
                        break;
                    }
                    if (deleteId <= 0) {
                        System.out.println("Transaction ID must be greater than zero!");
                        break;
                    }

                    manager.deleteTransaction(deleteId);
                    break;
                }

                case 9:
                    manager.saveTransactions();
                    System.out.println("Thank you for using Personal Finance Tracker!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
