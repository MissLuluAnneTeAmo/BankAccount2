import java.util.Scanner;



public class BankAccountList {

    // Maximum number of accounts allowed
    private final int capacity;
    // Array to store bank accounts
    private static BankAccount2[] bankAccount2s;
    // Number of accounts currently in the list
    private static int numOfAccount;

    private final Scanner scanner = new Scanner(System.in);  // Scanner for user input

    public BankAccountList() {
        // Default constructor sets capacity to maximum value (Short.MAX_VALUE)
        // and initializes an empty bank account array.
        capacity = Short.MAX_VALUE;
        bankAccount2s = new BankAccount2[capacity];
        numOfAccount = 0;
    }

    public BankAccountList(int capacity) {
        // Constructor with specified capacity for the bank account array.
        this.capacity = capacity;
        bankAccount2s = new BankAccount2[capacity];
        numOfAccount = 0;

    }

    public BankAccountList(BankAccount2[] lists)
    {
        // Constructor that takes an existing array of BankAccount2 objects.
        super();
        capacity = Short.MAX_VALUE;
        bankAccount2s = new BankAccount2[capacity];
        // Copy all elements
        System.arraycopy(bankAccount2s, 0, lists, 0, lists.length);
    }

    public void addAccount(BankAccount2 bankAccount2) {
        // Adds a BankAccount2 object to the list if there's space.
        if (numOfAccount < capacity) {
            bankAccount2s[numOfAccount] = bankAccount2;
            numOfAccount++;
        } else {
            System.out.println("\nBank account is full, cannot add more account.");
        }
    }

    public void updateAccount(BankAccount2 b, String name, String number) {
        // Updates the name and account number of a specific BankAccount2 object.
        b.setName(name);
        b.setAccountNumber(number);
    }

    public BankAccount2 searchByIndex(int i) {
        // Returns the BankAccount2 object at the specified index if valid.
        if (i >= 0 && i <= capacity) {
            return bankAccount2s[i];
        } else {
            return null;
        }

    }

    public BankAccount2 searchByName(String name) {
        // Searches for a BankAccount2 object containing the given name (case-insensitive).
        for (BankAccount2 b : bankAccount2s) {
            if (b != null) {
                if (b.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(b.getName().toLowerCase()))
                {
                    return b;
                }
            }
        }
        return null;
    }


    public BankAccount2 searchByAccountNumber(String accountNumber) {
        // Searches for a BankAccount2 object with the specified account number.
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccount2s[i].getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return bankAccount2s[i];
            }
        }
        return null;
    }

    public static void printBankAccount() {
        // Prints all BankAccount2 objects in the list.
        System.out.println("=========================");
        System.out.println("Accounts in the Bank:");
        for (int i = 0; i < numOfAccount; i++) {
            System.out.println(bankAccount2s[i]);
        }
    }

    public boolean deleteBankAccount(BankAccount2 bankAccount2, String directory) {
        // Deletes a BankAccount2 object from the list and updates the CSV file (if provided).
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccount2s[i] == bankAccount2) {
                BankAccount2[] tempArr = new BankAccount2[numOfAccount - 1];
                System.arraycopy(bankAccount2s, 0, tempArr, 0, i);
                System.arraycopy(bankAccount2s, i + 1, tempArr, i, numOfAccount - i - 1);
                bankAccount2s = tempArr;
                numOfAccount--;
                // Update CSV if directory provided
                BankAccountCSVHandler.writeCSV(directory, this);
                return true;
            }
        }
        return false;
    }

    // This method allows withdrawing money from a bank account
    public boolean withdraw(String accountNumber, double amount) {
        // Withdraws money from a specific account if it exists and has sufficient funds.
        BankAccount2 account = searchByAccountNumber(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);

                return true;
            } else {
                System.out.println("\nInsufficient funds for account " + accountNumber);
                return false;
            }
        } else {
            System.out.println("\nAccount not found");
            return false;
        }

        }

    public boolean deposit(String accountNumber, double amount) {
        // Deposits money into a specific account if it exists and the amount is positive.
        BankAccount2 account = searchByAccountNumber(accountNumber);
        if (account != null) {
            if (amount > 0) { // Check for positive deposit amount
                account.setBalance(account.getBalance() + amount);
                return true;
            } else {
                System.out.println("Invalid deposit amount. Please enter a positive value.");
                return false;
            }
        } else {
            System.out.println("\nAccount not found");
            return false;
        }
    }
    public BankAccount2[] getBankList()
    {
        // Returns the entire array of BankAccount2 objects.
        return bankAccount2s;
    }
}







