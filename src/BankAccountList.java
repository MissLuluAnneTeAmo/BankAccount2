import java.util.Scanner;



public class BankAccountList {

    private final int capacity;
    private static BankAccount2[] bankAccount2s;
    private static int numOfAccount;

    private final Scanner scanner = new Scanner(System.in);  // Scanner for user input

    public BankAccountList() {
        capacity = Short.MAX_VALUE;
        numOfAccount = 0;
    }

    public BankAccountList(int capacity) {
        this.capacity = capacity;
        bankAccount2s = new BankAccount2[capacity];
        numOfAccount = 0;

    }

    public void addAccount(BankAccount2 bankAccount2) {
        if (numOfAccount < capacity) {
            bankAccount2s[numOfAccount] = bankAccount2;
            numOfAccount++;
        } else {
            System.out.println("\nBank account is full, cannot add more account.");
        }
    }

    public void updateAccount(BankAccount2 b, String name, String number) {
        b.setName(name);
        b.setAccountNumber(number);
    }

    public BankAccount2 searchByIndex(int i) {
        if (i >= 0 && i <= capacity) {
            return bankAccount2s[i];
        } else {
            return null;
        }

    }

    public BankAccount2 searchByName(String name) {
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
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccount2s[i].getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return bankAccount2s[i];
            }
        }
        return null;
    }

    public static void printBankAccount() {
        System.out.println("=========================");
        System.out.println("Accounts in the Bank:");
        for (int i = 0; i < numOfAccount; i++) {
            System.out.println(bankAccount2s[i]);
        }
    }

    public boolean deleteBankAccount(BankAccount2 bankAccount2) {
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccount2s[i] == bankAccount2) {
                for (int j = i; j < numOfAccount - 1; j++) {
                    bankAccount2s[j] = bankAccount2s[j + 1];
                }
                numOfAccount--;
                return true;
            }
        }
        return false;
    }

    // This method allows withdrawing money from a bank account
    public boolean withdraw(String accountNumber, double amount) {
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
        return bankAccount2s;
    }
}







