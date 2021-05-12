/*
 * Name: Yuyang Liu, Jeffrey Lin
 * Date: May 5
 * Teacher: Mr. Ho
 * Description:
*/

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BenfordsLaw{
    public static void main(String[] args) throws FileNotFoundException{
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
                loadFile(userInput);
            }
            else if (userInput.equals(checkFraudOption)){
                loadFile(userInput);
            }
            else{
                System.out.println("Please type in a valid option.");
            }
        }while (!userInput.equals(exitCondition));

        customer.close();
        System.out.println("Program Terminated.");
    }

    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("3. Report on total Sales\n")
        .concat("4. Check for fraud in sales data\n")
        .concat("9. Quit\n")
        );
    }

    public static void loadFile(String userInput) throws FileNotFoundException{
        // Open File
        String fileName = "sales.csv";
        String line = "";
        int[] counters = new int[10];

        // Loading the file into the program
        FileReader saleFile = new FileReader(fileName);
        
        try {
          // Read the file by creating Scanner instance to read the file in the java
            BufferedReader br = new BufferedReader(saleFile);  
            while((line = br.readLine()) != null){
                String[] dataCollection = line.split(",");
                if(userInput.equals("3")){
                    System.out.println("postal code: " + dataCollection[0]);
                    System.out.println("sales: " + dataCollection[1]);
                }
                for(int i = 1; i <= 9; i++){
                    if (Character.getNumericValue(dataCollection[1].charAt(0)) == i){
                        counters[i] += 1;
                    }
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        if(userInput.equals("4")){
            reportResults(counters);
        }
    }

    public static void reportResults(int[] counters){
        int total = 0;
        for(int i = 1; i <= 9; i++){
            total += counters[i];
        }

        for(int i = 1; i <= 9; i++){
            double frequency = (counters[i]*100) / total;
            System.out.println("The distribution of first digit " + i + " is " + counters[i]);
            System.out.println("The distribution of first digit " + i + " is approximately " + frequency + " percent.");
        }
    }
}