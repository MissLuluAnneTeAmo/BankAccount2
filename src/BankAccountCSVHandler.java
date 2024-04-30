import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BankAccountCSVHandler {

    String[] bankAccountString;

    public static BankAccountList readCSV(String fileName)
    {
        BankAccountList list = new BankAccountList();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");
                double balance = Double.parseDouble(row[2]);
                BankAccount2 b = new BankAccount2(row[0], row[1], balance);
                list.addAccount(b);
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void writeCSV(String fileName, BankAccountList list)
    {
        try {
            FileWriter writer = new FileWriter(fileName);
            for(BankAccount2 bank : list.getBankList())
            {
                if(bank != null)
                {
                    String line = bank.getName() + "," + bank.getAccountNumber() + "," + bank.getBalance() + "\n";
                    writer.write(line);
                }
            }
            writer.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data written to CSV file: " + fileName);
    }
}
