

public class BankAccount {

    private String Name;
    private String AccountNumber;
    private double Balance;
    private double InterestRate;


    public BankAccount() {

        }


    public BankAccount(String Name, String AccountNumber, double Balance, double InterestRate ) {
        this.Name = Name;
        this.AccountNumber = AccountNumber;
        this.Balance = Balance;
        this.InterestRate = InterestRate;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public double getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(double interestRate) {
        InterestRate = interestRate;
    }

    public void addInterest() {
        // Calculate the interest earned
        double interest = Balance * InterestRate;

        // Add the interest to the balance
        Balance += interest;
    }

    public String toString() {
        return "\nNAME: " + Name +
                "\nACCOUNT NUMBER: " + AccountNumber +
                "\nBALANCE: " + Balance;

    }


}
