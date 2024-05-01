import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BankAccountCSVHandler {

    String[] bankAccountString;

    // Reads bank account data from a CSV file.
    public static void readCSV(String fileName)
    {
        BankAccountList list = new BankAccountList();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null)
            {
                // Split each line into comma-separated values
                String[] row = line.split(",");
                // Parse balance as a double
                double balance = Double.parseDouble(row[2]);
                // Create a BankAccount2 object
                BankAccount2 b = new BankAccount2(row[0], row[1], balance);
                // Add the object to the BankAccountList
                list.addAccount(b);
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);// Re-throw IOException as RuntimeException
        }
    }

    public static void writeCSV(String fileName, BankAccountList list)
    {
        // Writes bank account data to a CSV file.
        try {
            FileWriter writer = new FileWriter(fileName);
            // Write the header line
            writer.write("Bank Account,Account Number,Balance\n");

            if(list != null)
            {
                for(BankAccount2 bank : list.getBankList())
                {
                    if(bank != null)
                    {
                        String line = bank.getName() + "," + bank.getAccountNumber() + "," + bank.getBalance() + "\n";
                        writer.write(line);// Write each bank account's details
                    }
                }
            }
            writer.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);// Re-throw IOException as RuntimeException
        }
        System.out.println("Data written to CSV file: " + fileName);
    }
}
