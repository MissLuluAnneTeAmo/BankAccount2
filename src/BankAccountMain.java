import javax.lang.model.element.Name;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class BankAccountMain {


        private static Scanner scanner;


         public static void main (String[]args) {

             BankAccount acc1 = new BankAccount("Max", "9999", 8000, 0.05);
             BankAccount acc2 = new BankAccount("Ana", "8888", 9000, 0);
             BankAccount acc3 = new BankAccount("Terri", "7777", 7000, 0);
             BankAccount acc4 = new BankAccount("Fie", "2222", 1000, 0);


             BankAccountList List = new BankAccountList(100);
             List.addAccount(acc1);
             List.addAccount(acc2);
             List.addAccount(acc3);
             List.addAccount(acc4);


             //CHANGE ACCOUNT NAME =============================================================================================
             //acc1.setName("Rose");
             // acc2.setName("Lulu");

             //CHANGE ACCOUNT BALANCE=============================================================================================
             //acc1.setBalance(8000);
             // acc2.setBalance(9000);

             //SEARCH ACCOUNT NUMBER=============================================================================================
             List.searchByAccountNumber("9999");
             List.searchByAccountNumber("8888");

             //PRINTING===============================================================================================

             int choice = 0;
             String accountNumber;
             String accountName;
             double amount;
             String name;
             double interest;
             stuff:
             do {
                 System.out.println("====================================");
                 System.out.println("Bank Account Menu:");
                 System.out.println("====================================");
                 System.out.println("[1] Open Account");
                 System.out.println("[2] Search Account");
                 System.out.println("[3] Add New Account");
                 System.out.println("[4] Display All Account");// Added feature
                 System.out.println("[5] Exit");
                 System.out.println("====================================");
                 System.out.print("\nEnter your choice: ");
                 Scanner scanner = new Scanner(System.in);
                 try {
                     choice = scanner.nextInt();
                 } catch (InputMismatchException e) {
                     System.out.println("Invalid input.");
                     scanner.nextLine();
                 }

                 if (choice == 1) {
                     System.out.println("====================================");
                     System.out.print("Enter account number to open account: ");
                     scanner = new Scanner(System.in);
                     accountNumber = scanner.nextLine();
                     BankAccount account = List.searchByAccountNumber(accountNumber);
                     if (account != null) {
                         System.out.println("====================================");
                         System.out.println("Account Details:");
                         System.out.println(account); // Print account details

                         System.out.println("====================================");
                         System.out.println("\nOptions:");
                         System.out.println("====================================");
                         System.out.println("1. Withdraw Money");
                         System.out.println("2. Deposit Money");
                         System.out.println("3. Delete Account");
                         System.out.println("====================================");
                         System.out.print("Enter your choice (1 - 3): ");

                         int subChoice = 0;
                         try {
                             subChoice = scanner.nextInt();
                         } catch (InputMismatchException e) {
                             scanner.nextLine();
                             break;// Clear the scanner buffer
                             // Skip to the next iteration of the case block
                         }

                         switch (subChoice) {
                             case 1:
                                 System.out.print("\nEnter amount to withdraw: ");
                                 amount = scanner.nextDouble();
                                 boolean withdrawSuccess = List.withdraw(accountNumber, amount);
                                 if (withdrawSuccess) {
                                     System.out.println("====================================");

                                     System.out.println("\nWITHDREW: " + amount +
                                             "\nINTEREST: " + account.getInterestRate());
                                     account.addInterest();
                                     System.out.println("BALANCE WITH INTEREST: " + account.getBalance());
                                     System.out.println("Withdrawal successful.");
                                     System.out.println("====================================");
                                     System.out.println("Do you want to save the account? (y/n): ");
                                     scanner = new Scanner(System.in);
                                     String saveChoice = scanner.nextLine().toLowerCase();
                                     if (saveChoice.equals("y")) continue stuff;

                                 } else {
                                     System.out.println("\nWithdrawal failed. Check account or amount.");
                                 }
                                 scanner.nextLine(); // Clear the scanner buffer after readingnextDouble()
                                  continue;
                             case 2:
                                 System.out.print("\nEnter amount to deposit: ");
                                 amount = scanner.nextDouble();
                                 boolean depositSuccess = List.deposit(accountNumber, amount);
                                 if (depositSuccess) {
                                     System.out.println("====================================");

                                     System.out.println("DEPOSITED: " + amount +
                                             "\nBALANCE: " + account.getBalance());
                                     System.out.println("====================================");


                                     System.out.println("Deposit successful.");
                                 } else {
                                     System.out.println("Deposit failed. Check account or amount.");
                                 }
                                 scanner.nextLine();
                                 continue;// Clear the scanner buffer after readingnextDouble()
                             case 3:
                                 if (List.deleteBankAccount(account)) {
                                     System.out.println("Account deleted successfully.");
                                 } else {
                                     System.out.println("Account deletion failed. Account not found.");
                                 }

                             default:
                                 System.out.println("Invalid sub-choice. Please enter 1, 2, or 3.");
                         }
                     } else {
                         System.out.println("\nAccount not found.");

                     }
                 }


                 if (choice == 2) {
                     System.out.print("Enter account number to search: ");
                     scanner = new Scanner(System.in);
                     accountNumber = scanner.nextLine();
                     BankAccount account = List.searchByAccountNumber(accountNumber);
                     if (account != null) {
                         System.out.println("====================================");
                         System.out.println("\nAccount Details:");
                         System.out.println("====================================");

                         System.out.println(account); // Print account details

                         System.out.println("====================================");

                         System.out.println("\nOptions:");
                         System.out.println("1. Withdraw Money");
                         System.out.println("2. Deposit Money");
                         System.out.println("3. Delete Account");
                         System.out.print("Enter your choice (1, 2, or 3): ");

                         int subChoice = 0;
                         try {
                             subChoice = scanner.nextInt();
                         } catch (InputMismatchException e) {
                             System.out.println("Invalid input. Please enter a number between 1 and 3.");
                             scanner.nextLine();// Clear the scanner buffer// Skip to the next iteration of the case block
                         }

                         switch (subChoice) {
                             case 1:
                                 System.out.print("\nEnter amount to withdraw: ");
                                 amount = scanner.nextDouble();
                                 boolean withdrawSuccess = List.withdraw(accountNumber, amount);
                                 if (withdrawSuccess) {
                                     System.out.println("\nWITHDREW: " + amount +
                                             "\nINTEREST: " + account.getInterestRate());
                                     account.addInterest();
                                     System.out.println("BALANCE WITH INTEREST: " + account.getBalance());
                                     System.out.println("Withdrawal successful.");
                                 } else {
                                     System.out.println("\nWithdrawal failed. Check account or amount.");
                                 }
                                 scanner.nextLine(); // Clear the scanner buffer after readingnextDouble()
                             case 2:
                                 System.out.print("\nEnter amount to deposit: ");
                                 amount = scanner.nextDouble();
                                 boolean depositSuccess = List.deposit(accountNumber, amount);
                                 if (depositSuccess) {
                                     System.out.println("DEPOSITED: " + amount +
                                             "\nBALANCE: " + account.getBalance());
                                     System.out.println("Deposit successful.");
                                 } else {
                                     System.out.println("Deposit failed. Check account or amount.");
                                 }
                                 scanner.nextLine(); // Clear the scanner buffer after readingnextDouble()

                             case 3:
                                 if (List.deleteBankAccount(account)) {
                                     System.out.println("Account deleted successfully.");
                                 } else {
                                     System.out.println("Account deletion failed. Account not found.");
                                 }
                                 continue;
                             default:
                                 System.out.println("Invalid sub-choice. Please enter 1, 2, or 3.");
                         }
                     } else {
                         System.out.println("\nAccount not found.");
                     }
                 }

                 if (choice == 3) {
                     System.out.print("Enter account number: ");
                     scanner = new Scanner(System.in);
                     accountNumber = scanner.nextLine();
                     System.out.print("Enter account name: ");
                     scanner = new Scanner(System.in);
                     name = scanner.nextLine();
                     System.out.print("Enter initial balance: ");
                     scanner = new Scanner(System.in);
                     amount = scanner.nextDouble();
                     System.out.print("Enter interest: ");
                     scanner = new Scanner(System.in);
                     interest = scanner.nextDouble();

                     // Add error handling for invalid balance
                     if (amount < 0) {
                         System.out.println("Error: Initial balance cannot be negative.");
                     } else {
                         BankAccount newAccount = new BankAccount(name, accountNumber, amount, interest); // Set default interest rate

                         System.out.println("Do you want to save the new account? (y/n): ");
                         scanner = new Scanner(System.in);
                         String saveChoice = scanner.nextLine().toLowerCase(); // Convert input to lowercase

                         if (saveChoice.equals("y")) {
                             List.addAccount(newAccount);
                             System.out.println("Account added and saved successfully.");
                         } else {
                             System.out.println("Account creation cancelled. New account not saved.");
                         }
                     }

                 }

                 if (choice == 4) {
                     System.out.println("=========================");
                     BankAccountList.printBankAccount();
                     System.out.println("=========================");
                 }

                 if (choice == 5) {

                     System.out.println("Exiting Bank Account Menu.");
                     break;

                 }
             } while (choice == 6);
             }

         }









      /*//CHANGE ACCOUNT NAME =============================================================================================
        acc1.setName("Rose");
        acc2.setName("Lulu");

        //CHANGE ACCOUNT BALANCE=============================================================================================
        acc1.setBalance(90000);
        acc2.setBalance(40000);

        //SEARCH ACCOUNT NUMBER=============================================================================================
        List.searchByAccountNumber("9999");
        List.searchByAccountNumber("8888");
        List.printBankAccount();



        //WITHDRAWING AND ADDING INTEREST===================================================================================
        List.withdraw("9999", 1000);
        acc1.addInterest();
        System.out.println("INTEREST: " + acc1.getInterestRate() +
                "\nBALANCE WITH INTEREST: "+acc1.getBalance());
        //==================================================================================================================
        List.withdraw("8888", 1000);
        acc2.addInterest();
        System.out.println("INTEREST: " + acc2.getInterestRate() +
                "\nBALANCE WITH INTEREST: "+acc2.getBalance());

        //DELETING ACCOUNT===================================================================================================
        List.deleteBankAccount(acc1);
        List.deleteBankAccount(acc3);


        //PRINTING===============================================================================================


        List.printBankAccount();

    }*/


