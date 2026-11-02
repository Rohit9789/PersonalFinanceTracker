
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class FileHandler {

    public void saveTransactions(ArrayList<Transaction> transactions) {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("data/transactions.txt"))) {

            for (Transaction transaction : transactions) {

                writer.write(
                    transaction.getId() + "|" +
                    transaction.getType() + "|" +
                    transaction.getCategory() + "|" +
                    transaction.getAmount() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getDate()
                );

                writer.newLine();
            }

            System.out.println("Transactions saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving transactions!");
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> loadTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<Integer> loadedIds = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader("data/transactions.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data;

                if (line.contains("|")) {
                    data = line.split("\\|", -1);
                } else {
                    data = line.split(",", -1);
                }

                // Check whether the record has six fields
                if (data.length != 6) {
                    System.out.println("Skipping invalid transaction record!");
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0]);
                    if (id <= 0) {
                        System.out.println("Skipping transaction with invalid ID!");
                        continue;
                    }
                    if (loadedIds.contains(id)) {
                        System.out.println("Skipping transaction with duplicate ID!");
                        continue;
                    }

                    String type = data[1];
                    if (!type.equals("Income") && !type.equals("Expense")) {
                        System.out.println("Skipping transaction with invalid type!");
                        continue;
                    }

                    String category = data[2];
                    if (category.trim().isEmpty()) {
                        System.out.println("Skipping transaction with empty category!");
                        continue;
                    }

                    double amount = Double.parseDouble(data[3]);
                    if (amount <= 0 || !Double.isFinite(amount)) {
                        System.out.println("Skipping transaction with invalid amount!");
                        continue;
                    }

                    String description = data[4];
                    if (description.trim().isEmpty()) {
                        System.out.println("Skipping transaction with empty description!");
                        continue;
                    }

                    String date = data[5];

                    if (date.trim().isEmpty()) {
                        System.out.println("Skipping transaction with empty date!");
                        continue;
                    }
                    DateTimeFormatter formatter = DateTimeFormatter
                            .ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT);

                    try {
                        LocalDate.parse(date, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Skipping transaction with invalid date!");
                        continue;
                    }
                    if (date.trim().isEmpty()) {
                        System.out.println("Skipping transaction with empty date!");
                        continue;
                    }

                    Transaction transaction = new Transaction(
                        id, type, category, amount, description, date
                    );

                    transactions.add(transaction);
                    loadedIds.add(id);

                } catch (NumberFormatException e) {

                    System.out.println("Skipping transaction with invalid number!");
                }
            }

            System.out.println("Transactions loaded successfully!");

        } catch (IOException e) {

            System.out.println("Error while loading transactions!");
            e.printStackTrace();
        }

        return transactions;
    }
}
