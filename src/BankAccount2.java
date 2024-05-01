

public class BankAccount2 {

    private String Name;
    private String AccountNumber;
    private double Balance;
    private static double InterestRate;
    private double interestRate;
    /**
     * Constructs a BankAccount2 object with the specified name, account number, and balance.
     *
     * @param name        The name of the account holder.
     * @param accountNumber The account number.
     * @param balance      The initial balance of the account.
     */
    public BankAccount2(String name, String accountNumber, double balance) {
        this.Name = name;
        this.AccountNumber = accountNumber;
        this.Balance = balance;
    }

    /**
     * @deprecated This constructor is deprecated as the interest rate is not a mandatory attribute
     *             for creating a bank account. It is recommended to use the constructor that
     *             only takes name, account number, and balance.
     * Constructs a BankAccount2 object with the specified name, account number, balance, and interest rate.
     *
     */
    @Deprecated
    public BankAccount2(String Name, String AccountNumber, double Balance, double InterestRate ) {
        this.Name = Name;
        this.AccountNumber = AccountNumber;
        this.Balance = Balance;
        this.InterestRate = InterestRate;
    }



    // Getter and setter methods for Name, AccountNumber, Balance, and InterestRate
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

    public static void setInterestRate(double interestRate) {
        InterestRate = interestRate;
    }
    public void setInterest(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Calculates and adds interest to the account balance.
     */
    public void addInterest() {
        // Calculate the interest earned
        double interest = (1 + (InterestRate / 100));

        // Add the interest to the balance
        Balance += interest;
    }

    /**
     * Returns a string representation of the bank account object, including
     * the name, account number, and balance.
     *
     * @return A string representation of the bank account object.
     */
    public String toString() {
        return "\nNAME: " + Name +
                "\nACCOUNT NUMBER: " + AccountNumber +
                "\nBALANCE: " + Balance
                +"\nInterest Rate: " + InterestRate ;

    }


}
