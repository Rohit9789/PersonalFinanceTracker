public class Transaction {
    private int id;
    private String type;
    private String category;
    private double amount;
    private String description;
    private String date;

        public Transaction(int id, String type, String category,
                    double amount, String description, String date) {

        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
            public int getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public String getDate() {
            return date;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDate(String date) {
            this.date = date;
        }

}
