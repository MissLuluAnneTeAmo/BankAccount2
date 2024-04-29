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
                BankAccount b = new BankAccount(row[0], row[1], balance);
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
            for(BankAccount bank : list.getBankList())
            {
                for(int i = 0; i < list.getBankList().length; i++)
                {
                    String line = bank.getName() + "," + bank.getAccountNumber() + "," + bank.getBalance();
                    writer.write(line + "\n");
                }
                writer.append("\n");
            }
            writer.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data written to CSV file: " + fileName);
    }
}
