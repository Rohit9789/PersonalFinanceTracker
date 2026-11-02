# Personal Finance & Expense Tracker

A Java console-based application to manage personal income and expenses.

## Features

- Add income transactions
- Add expense transactions
- View all transactions
- Calculate total income
- Calculate total expenses
- Check available balance
- Search transactions by ID
- Delete transactions by ID
- Save transactions using file handling
- Load saved transactions when the application starts
- Validate user input and transaction records
- Prevent duplicate transaction IDs

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- File Handling
- Exception Handling
- Java Date and Time API

## Project Structure

```text
PersonalFinanceTracker/
├── data/
│   ├── transactions.txt
│   └── transactions_backup.txt
├── src/
│   ├── Main.java
│   ├── Transaction.java
│   ├── TransactionManager.java
│   ├── FileHandler.java
│   └── TransactionManagerTest.java
└── README.md
```

## How to Run

1. Install Java JDK.
2. Open the project folder in VS Code.
3. Open the terminal in the project root.
4. Compile the Java files:

```powershell
javac -d out src/*.java
```

5. Run the application:

```powershell
java -cp out Main
```

## Learning Objectives

Through this project, I practised:

- Java fundamentals and OOP concepts
- Classes, objects and constructors
- Java Collections Framework (ArrayList)
- File handling and data persistence
- Exception handling
- Input validation
- Basic testing using Java assertions

## Future Improvements

- Database integration using SQL and JDBC
- Monthly expense reports
- Graphical user interface
- Improved transaction filtering

---

**Project Status:** Core console application completed.