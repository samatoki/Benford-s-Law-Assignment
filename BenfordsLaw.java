/*
 * Name: Yuyang Liu, Jeffrey Lin
 * Date: May 5
 * Teacher: Mr. Ho
 * Description:
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BenfordsLaw{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Initialize variables
        Scanner customer = new Scanner(System.in);
        String userInput, reportSalesOption, checkFraudOption, exitCondition;
        reportSalesOption = "3";
        checkFraudOption = "4";
        exitCondition = "9";

        do{
            printMenu();
            userInput = customer.nextLine();
            
            if (userInput.equals(reportSalesOption)){
                loadFile();
            }
            
        }while (!userInput.equals(exitCondition));

        customer.close();
    }

    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("3. Report on total Sales\n")
        .concat("4. Check for fraud in sales data\n")
        .concat("9. Quit\n")
        );
    }
    
    public static void loadFile() throws FileNotFoundException{
        // Open File
        String fileName = "sales.csv";
        // Loading the file into the program
        File saleFile = new File(fileName);
        
        // Read the file by creating Scanner instance to read the file in the java
        Scanner reader = new Scanner(saleFile);
        // Sets the delimiter pattern
        reader.useDelimiter(",");
        // Create and initialize an arraylist to store the sales num
        ArrayList<String> salesArr = new ArrayList<String>();
        
        while (reader.hasNext())
        {
            salesArr.add(reader.next());
        }

        System.out.println(salesArr);
        System.out.println();
        reader.close();
    }
        
}