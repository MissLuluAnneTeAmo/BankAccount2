import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class BankAccountGUI extends JFrame implements ActionListener {

            private List bankAccounts; // Reference to bank account list
            private JTextField accountNumberField, nameField, balanceField, interestRateField, amountField;
            private JButton searchButton, depositButton, withdrawButton, addAccountButton, deleteAccountButton, printAllButton;
            private JTextArea outputTextArea;

        public BankAccountGUI(List bankAccounts) {
                super("Bank Account Management");
                this.bankAccounts = bankAccounts;
                initializeComponents();
                createLayout();
                addListeners();
            }

            private void initializeComponents () {
                accountNumberField = new JTextField(15);
                nameField = new JTextField(15);
                balanceField = new JTextField(15);
                interestRateField = new JTextField(15);
                amountField = new JTextField(15);

                searchButton = new JButton("Search");
                depositButton = new JButton("Deposit");
                withdrawButton = new JButton("Withdraw");
                addAccountButton = new JButton("Add Account");
                deleteAccountButton = new JButton("Delete Account");
                printAllButton = new JButton("Print All");

                outputTextArea = new JTextArea(10, 30);
                outputTextArea.setEditable(false);
            }

            private void createLayout () {
                // Use a layout manager (e.g., GridLayout, BorderLayout) to arrange components
                // ... (implementation details)
            }

            private void addListeners () {
                searchButton.addActionListener(this);
                depositButton.addActionListener(this);
                withdrawButton.addActionListener(this);
                addAccountButton.addActionListener(this);
                deleteAccountButton.addActionListener(this);
                printAllButton.addActionListener(this);
            }

            @Override
            public void actionPerformed (ActionEvent e){
                JButton clickedButton = (JButton) e.getSource();

                if (clickedButton == searchButton) {
                    // Search for account and display details in outputTextArea
                    // ... (implementation details)
                } else if (clickedButton == depositButton) {
                    // Get account number, amount, and perform deposit
                    // ... (implementation details)
                } else if (clickedButton == withdrawButton) {
                    // Get account number, amount, and perform withdrawal
                    // ... (implementation details)
                } else if (clickedButton == addAccountButton) {
                    // Get account details and create a new account object
                    // ... (implementation details)
                } else if (clickedButton == deleteAccountButton) {
                    // Get account number and attempt deletion
                    // ... (implementation details)
                } else if (clickedButton == printAllButton) {
                    // Display all accounts in the outputTextArea
                    // ... (implementation details)
                }
            }
        }


