import java.util.*;

class ATMInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class ATM {
    private Scanner sc = new Scanner(System.in);
    private User user = new User("admin", "007", 10000);

    public void start() {
        System.out.println("===== Welcome to ATM Machine =====");

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (user.validate(userId, pin)) {
            System.out.println("\nLogin Successful!\n");
            menu();
        } else {
            System.out.println("\nInvalid User ID or PIN!");
        }
    }

    private void menu() {
        int choice;
        do {
            System.out.println("===== ATM Menu =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.showHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using ATM!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        int amount = sc.nextInt();
        user.withdraw(amount);
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        int amount = sc.nextInt();
        user.deposit(amount);
    }

    private void transfer() {
        System.out.print("Enter amount to transfer: ");
        int amount = sc.nextInt();
        user.transfer(amount);
    }
}

class User {
    private String userId;
    private String pin;
    private int balance;
    private ArrayList<String> history = new ArrayList<>();

    public User(String userId, String pin, int balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validate(String uid, String p) {
        return userId.equals(uid) && pin.equals(p);
    }

    public void withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Withdrawn: ₹" + amount);
            System.out.println("Withdrawal Successful!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void deposit(int amount) {
        balance += amount;
        history.add("Deposited: ₹" + amount);
        System.out.println("Deposit Successful!");
    }

    public void transfer(int amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Transferred: ₹" + amount);
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void showHistory() {
        System.out.println("\n--- Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
        System.out.println("Current Balance: ₹" + balance + "\n");
    }
}