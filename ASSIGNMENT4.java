import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

// Custom Exception Classes
class AccountIdException extends Exception {
    public AccountIdException(String msg) { super(msg); }
}

class AmountValueException extends Exception {
    public AmountValueException(String msg) { super(msg); }
}

class BalanceLimitException extends Exception {
    public BalanceLimitException(String msg) { super(msg); }
}

class FundShortageException extends Exception {
    public FundShortageException(String msg) { super(msg); }
}

class AccountHolder {
    private final int accId;
    private final String holderName;
    private double balance;

    public AccountHolder(int accId, String holderName, double balance) {
        this.accId = accId;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccId() { return accId; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void deduct(double amt) {
        balance -= amt;
    }

    public String formatForFile() {
        return accId + "|" + holderName + "|" + String.format("%.2f", balance);
    }
}

class BankManager {
    private static final double STARTING_MIN = 1500.0;
    private final Map<Integer, AccountHolder> accountMap = new HashMap<>();
    private final File dataFile;

    public BankManager(String path) {
        this.dataFile = new File(path);
    }

    public void openAccount(int id, String name, double initialDeposit)
            throws AccountIdException, AmountValueException, BalanceLimitException {
        if (id < 1 || id > 25) throw new AccountIdException("ID must be between 1 and 25.");
        if (initialDeposit <= 0) throw new AmountValueException("Initial deposit must be positive.");
        if (initialDeposit < STARTING_MIN) throw new BalanceLimitException("Minimum 1500 required for new accounts.");

        accountMap.put(id, new AccountHolder(id, name, initialDeposit));
        saveData();
    }

    public void processWithdrawal(int id, double amt)
            throws AccountIdException, AmountValueException, FundShortageException {
        if (!accountMap.containsKey(id)) throw new AccountIdException("Account ID " + id + " not found.");
        if (amt <= 0) throw new AmountValueException("Withdrawal amount must be positive.");

        AccountHolder user = accountMap.get(id);
        if (amt > user.getBalance()) throw new FundShortageException("Insufficient balance for this transaction.");

        user.deduct(amt);
        saveData();
    }

    public void showAllRecords() {
        if (accountMap.isEmpty()) {
            System.out.println("--- No Records Found ---");
            return;
        }
        System.out.println("\n--- Current Account Holders ---");
        for (AccountHolder user : accountMap.values()) {
            System.out.printf("ID: %d | Name: %s | Balance: $%.2f%n", 
                user.getAccId(), user.getHolderName(), user.getBalance());
        }
    }

    private void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            bw.write("ID|Name|Balance");
            bw.newLine();
            for (AccountHolder user : accountMap.values()) {
                bw.write(user.formatForFile());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
}

public class ASSIGNMENT4 {
    public static void main(String[] args) {
        BankManager manager = new BankManager("bank_data.csv");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[ BANKING PORTAL ]");
            System.out.println("1. New Account\n2. Withdraw\n3. List All\n4. Exit");
            System.out.print("Select action: ");
            
            try {
                int input = sc.nextInt();
                sc.nextLine(); 

                switch (input) {
                    case 1:
                        System.out.print("Enter ID (1-25): ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Initial Deposit: ");
                        double dep = sc.nextDouble();
                        manager.openAccount(id, name, dep);
                        System.out.println("Success!");
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        int wid = sc.nextInt();
                        System.out.print("Amount: ");
                        double wamt = sc.nextDouble();
                        manager.processWithdrawal(wid, wamt);
                        System.out.println("Withdrawal complete.");
                        break;
                    case 3:
                        manager.showAllRecords();
                        break;
                    case 4:
                        System.out.println("System Shutdown.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Alert: " + e.getMessage());
                sc.nextLine(); 
            }
        }
    }
}