import java.io.*;
import java.util.Collection;
import java.util.Scanner;



public class BankAccountList {

    private final int capacity;
    private static BankAccount[] bankAccounts;
    private static int numOfAccount;

    private final Scanner scanner = new Scanner(System.in);  // Scanner for user input

    public BankAccountList() {
        capacity = Short.MAX_VALUE;
        numOfAccount = 0;
    }

    public BankAccountList(int capacity) {
        this.capacity = capacity;
        bankAccounts = new BankAccount[capacity];
        numOfAccount = 0;

    }

    public void addAccount(BankAccount bankAccount) {
        if (numOfAccount < capacity) {
            bankAccounts[numOfAccount] = bankAccount;
            numOfAccount++;
        } else {
            System.out.println("\nBank account is full, cannot add more account.");
        }
    }

    public void updateAccount(BankAccount b, String name, String number) {
        b.setName(name);
        b.setAccountNumber(number);
    }

    public BankAccount searchByIndex(int i) {
        if (i >= 0 && i <= capacity) {
            return bankAccounts[i];
        } else {
            return null;
        }

    }

    public BankAccount searchByName(String name) {
        for (BankAccount b : bankAccounts) {
            if (b != null) {
                if (b.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(b.getName().toLowerCase()))
                {
                    return b;
                }
            }
        }
        return null;
    }


    public BankAccount searchByAccountNumber(String accountNumber) {
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccounts[i].getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return bankAccounts[i];
            }
        }
        return null;
    }

    public static void printBankAccount() {
        System.out.println("=================================================");
        System.out.println("Accounts in the Bank:");
        for (int i = 0; i < numOfAccount; i++) {
            System.out.println(bankAccounts[i]);
        }
    }

    public boolean deleteBankAccount(BankAccount bankAccount) {
        for (int i = 0; i < numOfAccount; i++) {
            if (bankAccounts[i] == bankAccount) {
                for (int j = i; j < numOfAccount - 1; j++) {
                    bankAccounts[j] = bankAccounts[j + 1];
                }
                numOfAccount--;
                return true;
            }
        }
        return false;
    }

    // This method allows withdrawing money from a bank account
    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = searchByAccountNumber(accountNumber);
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
        BankAccount account = searchByAccountNumber(accountNumber);
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


    public BankAccount[] getBankList()
    {
        return bankAccounts;
    }
}







