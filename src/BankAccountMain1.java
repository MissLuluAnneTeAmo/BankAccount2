import java.util.Scanner;

public class BankAccountMain1 {


    public static void main(String[] args) {
        char choice = ' ';
        BankAccountList List = new BankAccountList(100);
        BankAccount acc1 = new BankAccount("Max", "9999", 8000, 0.05);
        List.addAccount(acc1);

        String accountNumber;
        String accountName;
        double amount;
        String name;
        double interest;


        do {
            System.out.println("====================================");
            System.out.println("Bank Account Menu:");
            System.out.println("====================================");
            System.out.println("[O] Open Account");
            System.out.println("[S] Search Account");
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

                }
                case 'd' -> {
                }
                default -> {}
            }


        } while (choice == 'y');
    }
}