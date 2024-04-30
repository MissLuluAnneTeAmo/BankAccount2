import java.util.Scanner;
import java.util.InputMismatchException;

public class BankAccountMain
{
    private static final BankAccountList List = new BankAccountList(100);

    public static void main (String[]args) {

         BankAccount2 acc1 = new BankAccount2("Max", "9999", 8000, 0.05);
         BankAccount2 acc2 = new BankAccount2("Ana", "8888", 9000, 0);
         BankAccount2 acc3 = new BankAccount2("Terri", "7777", 7000, 0);
         BankAccount2 acc4 = new BankAccount2("Fie", "2222", 1000, 0);
         List.addAccount(acc1);
         List.addAccount(acc2);
         List.addAccount(acc3);
         List.addAccount(acc4);
         List.searchByAccountNumber("9999");
         List.searchByAccountNumber("8888");

         char choice = ' ';
         String accountNumber;
         String accountName;
         double amount;
         String name;
         double interest;
         do
         {
             System.out.println("====================================");
             System.out.println("Bank Account Menu:");
             System.out.println("====================================");
             System.out.println("[S] Search Account");
             System.out.println("[A] Add New Account");
             System.out.println("[D] Display All Account");
             System.out.println("[E] Exit");
             System.out.println("====================================");
             System.out.print("\nEnter your choice: ");
             Scanner scanner = new Scanner(System.in);
             try
             {
                 choice = scanner.nextLine().toLowerCase().charAt(0);
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input.");
                 scanner.nextLine();
                 continue;
             }
             if (choice == 's')
             {
                 searchAccount();
             }

             if (choice == 'a') {
                 System.out.print("\nEnter account number: ");
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

                 if (amount < 0)
                 {
                     System.out.println("!Error: Initial balance cannot be negative!");
                 }
                 else
                 {
                     BankAccount2 newAccount = new BankAccount2(name, accountNumber, amount, interest); // Set default interest rate

                     System.out.println("\n------------------------------------------");
                     System.out.println("\nDo you want to save the new account? (y/n): ");
                     scanner = new Scanner(System.in);
                     String saveChoice = scanner.nextLine().toLowerCase(); // Convert input to lowercase

                     if (saveChoice.equals("y"))
                     {
                         List.addAccount(newAccount);
                         System.out.println("\n-------------------------------------");
                         System.out.println("\nAccount added and saved successfully.");
                         System.out.println("\n-------------------------------------");

                     }
                     else
                     {
                         System.out.println("Account creation cancelled. New account not saved.");
                     }
                 }

             }

             if (choice == 'd') {
                 System.out.println("\n=========================");
                 BankAccountList.printBankAccount();
                 System.out.println("\n=========================");
             }
         } while (choice != 'e');
        System.out.println("\n--------------------------------------");
        System.out.println("\n     Exiting Bank Account Menu.");
        System.out.println("\n--------------------------------------");

        System.exit(0);
     }

    private static void openAccount(BankAccount2 b)
    {
        System.out.println("====================================");
        System.out.println("Account Details:");
        System.out.println(b); // Print account details

        System.out.println("====================================");
        System.out.println("\nOptions:");
        System.out.println("====================================");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit Money");
        System.out.println("3. Delete Account");
        System.out.println("====================================");
        System.out.print("Enter your choice (1 - 3): ");

        int subChoice = new Scanner(System.in).nextInt();
        double amount;
        switch (subChoice) {
            case 1:
                System.out.print("\nEnter amount to withdraw: ");
                amount = new Scanner(System.in).nextDouble();
                boolean withdrawSuccess = List.withdraw(b.getAccountNumber(), amount);
                if (withdrawSuccess) {
                    System.out.println("====================================");

                    System.out.println("\nWITHDREW: " + amount +
                            "\nINTEREST: " + b.getInterestRate());
                    b.addInterest();
                    System.out.println("BALANCE WITH INTEREST: " + b.getBalance());
                    System.out.println("====================================");
                    System.out.println("\n--------------------------------------");
                    System.out.println("\n        Withdrawal successful.");
                    System.out.println("\n--------------------------------------");


                } else {
                    System.out.println("\n!Withdrawal failed. Check account or amount!");
                }
                new Scanner(System.in).nextLine();
                break;
            case 2:
                System.out.print("\nEnter amount to deposit: ");
                amount = new Scanner(System.in).nextDouble();
                boolean depositSuccess = List.deposit(b.getAccountNumber(), amount);
                if (depositSuccess) {
                    System.out.println("====================================");

                    System.out.println("DEPOSITED: " + amount +
                            "\nBALANCE: " + b.getBalance());
                    System.out.println("====================================");


                    System.out.println("\n--------------------------------------");
                    System.out.println("\n        Deposit successful.");
                    System.out.println("\n--------------------------------------");

                } else {
                    System.out.println("Deposit failed. Check account or amount.");
                }
                new Scanner(System.in).nextLine();
                break;
            case 3:
                if (List.deleteBankAccount(b)) {
                    System.out.println("\n--------------------------------------");
                    System.out.println("\n     Account deleted successfully.");
                    System.out.println("\n--------------------------------------");
                } else {
                    System.out.println("Account deletion failed. Account not found.");
                }
                break;
            default:
                System.out.println("Invalid sub-choice. Please enter 1, 2, or 3.");
        }
    }

    private static void searchAccount()
    {
        System.out.println("\n--------------------------------------");
        System.out.println("[I] Search by index");
        System.out.println("[A] Search by account number");
        System.out.println("[N] Search by name");
        System.out.println("--------------------------------------");
        System.out.print("Enter: ");
        Scanner scanner = new Scanner(System.in);
        char c = scanner.nextLine().toLowerCase().charAt(0);
        switch(c)
        {
            case 'i':
            {
                System.out.println("\n====================================");
                System.out.print("Enter account index to open account: ");
                scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                BankAccount2 account = List.searchByIndex(i);
                openAccount(account);
                break;
            }
            case 'a':
            {
                System.out.println("\n====================================");
                System.out.print("Enter account number to open account: ");
                scanner = new Scanner(System.in);
                String accountNumber = scanner.nextLine();
                BankAccount2 account = List.searchByAccountNumber(accountNumber);
                openAccount(account);
                break;
            }
            case 'n':
            {
                System.out.println("\n====================================");
                System.out.print("Enter account name to open account: ");
                scanner = new Scanner(System.in);
                String accountName = scanner.nextLine();
                BankAccount2 account = List.searchByName(accountName);
                openAccount(account);
                break;
            }
        }
    }

}



