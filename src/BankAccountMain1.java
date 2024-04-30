import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccountMain1 {


    public static void main(String[] args) {
        char choice = ' ';
        BankAccountList List = new BankAccountList(100);
        BankAccount2 acc1 = new BankAccount2("Max", "9999", 8000);
        List.addAccount(acc1);

        String accountNumber;
        String accountName;
        double amount;
        String name;
        int index;
        double balance;




        do {
            System.out.println("====================================");
            System.out.println("Bank Account Menu:");
            System.out.println("====================================");
            System.out.println("[O] Open Account");
            System.out.println("[A] Add New Account");
            System.out.println("[D] Display All Account");// Added feature
            System.out.println("[E] Exit");
            System.out.println("====================================");
            System.out.print("\nEnter your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine().toLowerCase().charAt(0);

            switch (choice)
            {
                case 'o' ->
                {
                    System.out.println("[I] Search by index");
                    System.out.println("[A] Search by account number");
                    System.out.println("[N] Search by  name");
                    System.out.println("Enter: ");
                    scanner = new Scanner(System.in);
                    String str = scanner.nextLine();
                    switch(str)
                    {
                        case "i" ->
                        {
                            System.out.print("Enter account index to open account: ");
                            scanner = new Scanner(System.in);
                            int i = scanner.nextInt();
                            BankAccount2 account = List.searchByIndex(i);
                        }
                        case "a" ->
                        {
                            System.out.print("Enter account number to open account: ");
                            scanner = new Scanner(System.in);
                            accountNumber = scanner.nextLine();
                            BankAccount2 account = List.searchByAccountNumber(accountNumber);
                        }
                        case "n" ->
                        {
                            System.out.print("Enter account name to open account: ");
                            scanner = new Scanner(System.in);
                            accountName = scanner.nextLine();
                            BankAccount2 account = List.searchByName(accountName);
                        }
                        default ->
                        {
                            throw new InputMismatchException();
                        }
                    }



                }
                case 'a' -> {
                    System.out.print("Enter account number: ");
                    scanner = new Scanner(System.in);
                    accountNumber = scanner.nextLine();
                    BankAccount2 account = List.searchByAccountNumber(accountNumber);
                    System.out.print("Enter account name: ");
                    scanner = new Scanner(System.in);
                    name = scanner.nextLine();
                    account = List.searchByName(name);

                }
                default -> {}
            }


        } while (choice == 'y');
    }
}