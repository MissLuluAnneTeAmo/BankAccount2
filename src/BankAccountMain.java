import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BankAccountMain
{
    private static final BankAccountList List = new BankAccountList();
    public static boolean breaking = false;

    public static char choice = ' ';
    public static String accountNumber;
    public static String accountName;
    public static double amount;
    public static String name;
    public static double interest;
    // File path for CSV data
    private static final String directory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "LoggingFile.csv";

    public static void main (String[]args)
    {

         createLogs();// Check/create and read CSV file

         do
         {
             System.out.println("====================================");
             System.out.println("             WELCOME");
             System.out.println("====================================");
             System.out.println("BANK ACCOUNT MENU: ");
             System.out.println("[S] Search Account");
             System.out.println("[A] Add New Account");
             System.out.println("[D] Display All Account");
             System.out.println("[I] Set Interest Rate");
             System.out.println("[E] Exit");
             System.out.println("====================================");
             System.out.print("\nEnter your choice: ");
             Scanner scanner = new Scanner(System.in);
             try
             {
                 choice = scanner.nextLine().toLowerCase().charAt(0);
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input.");
                 scanner.nextLine(); // Consume invalid input
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
                 do {
                     try {
                         System.out.print("Enter initial balance: ");
                         scanner = new Scanner(System.in);
                         amount = scanner.nextDouble();
                         breaking = false;
                     }
                     catch(Exception e) {
                         System.out.println("\n\n\nInvalid balance amount!");
                         breaking = true;
                     }
                 } while(breaking);

                 if (amount < 0)
                 {
                     System.out.println("!Error: Initial balance cannot be negative!");
                 }
                 else
                 {
                     BankAccount2 newAccount = new BankAccount2(name, accountNumber, amount); // Set default interest rate

                     do
                     {
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
                             breaking = false;

                         }
                         else if (saveChoice.equals("n"))
                         {
                             System.out.println("Account creation cancelled. New account not saved.");
                             breaking = true;
                         }
                         else
                         {
                             System.out.println("\n\nInvalid choice!");
                             breaking = true;
                         }
                     } while(breaking);
                 }

             }

             if (choice == 'd') {
                 System.out.println("\n=========================");
                 BankAccountList.printBankAccount();
                 System.out.println("\n=========================");
                 System.out.println("[M] Menu");

             }
             if  (choice == 'i'){
                 try {
                     System.out.println("Set interest rate: ");
                     scanner = new Scanner(System.in);
                     interest = scanner.nextDouble();
                     BankAccount2.setInterestRate(interest);
                 }
                 catch(Exception e) {
                     System.out.println("Invalid interest number!");
                 }

             }
         } while (choice != 'e');
        // Exit loop and saving it on CSV file
        System.out.println("\n--------------------------------------");
        System.out.println("\n     Exiting Bank Account Menu.");
        System.out.println("\n--------------------------------------");
        BankAccountCSVHandler.writeCSV(directory, List); // Write data back to CSV
        System.exit(0);
     }

    private static void createLogs()
    {
        if(!new File(directory).exists())
        {
            // Create empty CSV if it doesn't exist
            BankAccountCSVHandler.writeCSV(directory, null);
        }
        else
            // Read existing CSV data
            BankAccountCSVHandler.readCSV(directory);
    }
    private static void openAccount(BankAccount2 b) {
        System.out.println("====================================");
        System.out.println("Account Details:");
        System.out.println(b); // Print account details

        System.out.println("====================================");
        System.out.println("\nOptions:");
        System.out.println("====================================");

        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit Money");
        System.out.println("3. Menu");
        System.out.println("4. Delete Account");
        System.out.println("5. Add Interest");
        System.out.println("5. Set Interest");

        System.out.println("====================================");

        System.out.print("Enter your choice");

        int subChoice = new Scanner(System.in).nextInt();
        double amount;
        switch (subChoice) {
            case 1:
                System.out.print("\nEnter amount to withdraw: ");
                amount = new Scanner(System.in).nextDouble();
                boolean withdrawSuccess = List.withdraw(b.getAccountNumber(), amount);
                if (withdrawSuccess) {
                    System.out.println("\n--------------------------------------");
                    System.out.println("\n        Withdrawal successful.");
                    System.out.println("\n--------------------------------------");


                } else {
                    System.out.println("\n!Withdrawal failed. Check account or amount!");
                    new Scanner(System.in).nextLine();
                }
                break;
            case 2:
                System.out.println("Apply Interest Rate? (y/n) ");
                Scanner scanner = new Scanner(System.in);
                String applyInterest = scanner.nextLine().toLowerCase();
                if (applyInterest.equals("y")) {
                    // Proceed with deposit and add interest
                    System.out.print("\nEnter amount to deposit: ");
                    amount = new Scanner(System.in).nextDouble();
                    boolean depositSuccess = List.deposit(b.getAccountNumber(), amount);
                    if (depositSuccess) {
                        b.addInterest(); // Add interest only if user chooses to apply it
                        System.out.println("\n--------------------------------------");
                        System.out.println("\n        Deposit successful.");
                        System.out.println("\n--------------------------------------");
                    } else {
                        System.out.println("Deposit failed. Check account or amount.");
                        new Scanner(System.in).nextLine();
                    }
                }

                if (applyInterest.equals("n")){
                System.out.print("\nEnter amount to deposit: ");
                amount = new Scanner(System.in).nextDouble();
                boolean depositSuccess = List.deposit(b.getAccountNumber(), amount);
                if (depositSuccess) {
                    System.out.println("\n--------------------------------------");
                    System.out.println("\n        Deposit successful.");
                    System.out.println("\n--------------------------------------");
                } else {
                    System.out.println("Deposit failed. Check account or amount.");
                    new Scanner(System.in).nextLine();
                }
            }
                break;
            case 3: break;
            case 4:
                if (List.deleteBankAccount(b, directory)) {
                    System.out.println("\n--------------------------------------");
                    System.out.println("\n     Account deleted successfully.");
                    System.out.println("\n--------------------------------------");
                } else {
                    System.out.println("Account deletion failed. Account not found.");
                }
                break;
            case 5:
                System.out.println("Set interest rate to your bank account: ");
                int interest = new Scanner(System.in).nextInt();
                b.setInterest((double) interest / 100);
            case 6:
                System.out.println("Update name: ");
                String name = new Scanner(System.in).nextLine();
                b.setName(name);
                System.out.println("\n--------------------------------------");
                System.out.println("\n     Account edited successfully.");
                System.out.println("\n--------------------------------------");

            default:
                System.out.println("Invalid sub-choice. Please enter 1 - 4.");
        }
    }

    private static void searchAccount()
    {
        do {
            System.out.println("\nSearch your account.");
            System.out.println("--------------------------------------");
            System.out.println("[I] Search by index");
            System.out.println("[A] Search by account number");
            System.out.println("[N] Search by name");
            System.out.println("[M] Menu");
            System.out.println("--------------------------------------");
            System.out.print("Enter: ");
            breaking = false;
            Scanner scanner = new Scanner(System.in);
            char c = 0;
            try {
                c = scanner.nextLine().toLowerCase().charAt(0);
            }
            catch(Exception e) {
                System.out.println("Invalid character!");
                breaking = true;
            }

            switch(c)
            {
                case 'i':
                {
                    System.out.println("\n====================================");
                    System.out.print("Enter account index to open account: ");
                    scanner = new Scanner(System.in);
                    int i;
                    try {
                        i = scanner.nextInt();
                    }
                    catch(Exception e) {
                        System.out.println("Invalid index number!");
                        breaking = true;
                        continue;
                    }
                    BankAccount2 account = List.searchByIndex(i);
                    if (account != null) {
                        openAccount(account);// Pass flag indicating searched account
                    } else {
                        System.out.println("\nAccount not found.");
                        breaking = true;
                    }
                    break;
                }

                case 'a':
                {
                    System.out.println("\n====================================");
                    System.out.print("Enter account number to open account: ");
                    scanner = new Scanner(System.in);
                    String accountNumber = scanner.nextLine();
                    BankAccount2 account = List.searchByAccountNumber(accountNumber);
                    if (account != null) {
                        openAccount(account);// Pass flag indicating searched account
                    } else {
                        System.out.println("\nAccount not found.");
                        breaking = true;
                    }
                    break;
                }
                case 'n':
                {
                    System.out.println("\n====================================");
                    System.out.print("Enter account name to open account: ");
                    scanner = new Scanner(System.in);
                    String accountName = scanner.nextLine();
                    BankAccount2 account = List.searchByName(accountName);
                    if (account != null) {
                        openAccount(account);// Pass flag indicating searched account
                    } else {
                        System.out.println("\nAccount not found.");
                        breaking = true;
                    }
                    break;
                }
                case 'm':
                    break;
                default:
                    breaking = true;
                    break;
            }
        } while(breaking);


    }


}




