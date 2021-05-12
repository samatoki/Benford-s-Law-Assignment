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
            else if (userInput.equals(checkFraudOption)){
                String File = "sales.csv";
                ReadFile(File);
            }
            else{
                System.out.println("Please type in a valid option.");
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
        String line = "";
        // Loading the file into the program
        FileReader saleFile = new FileReader(fileName);
        
        
        try {
          // Read the file by creating Scanner instance to read the file in the java
            BufferedReader br = new BufferedReader(saleFile);  
            while((line = br.readLine()) != null){
                String[] dataCollection = line.split(",");
                System.out.println("postal code: " + dataCollection[0]);
                System.out.println("sales: " + dataCollection[1]);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(dataCollection[0]);
    }
    
    public static void ReadFile(String fileName) {
        
        String line = "";
        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            
            int counter1 = 0;
            int counter2 = 0;
            int counter3 = 0;
            int counter4 = 0;
            int counter5 = 0;
            int counter6 = 0;
            int counter7 = 0;
            int counter8 = 0;
            int counter9 = 0;

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                
                char len = values[1].charAt(0);
                int firstdigits = Character.getNumericValue(len);
                // System.out.println(firstdigits);
                if (firstdigits == 1){
                    counter1+= 1;
                }
                else if(firstdigits == 2){
                    counter2+= 1;
                }
                else if(firstdigits == 3){
                    counter3+= 1;
                }
                else if(firstdigits == 4){
                    counter4+= 1;
                }              
                else if(firstdigits == 5){
                    counter5+= 1;
                }               
                else if(firstdigits == 6){
                    counter6+= 1;
                }
                else if(firstdigits == 7){
                    counter7+= 1;
                }           
                else if(firstdigits == 8){
                    counter8+= 1;
                }           
                else{
                    counter9+= 1;
                }               

            }
            System.out.println("The total number of digits that starts with 1 is: " + counter1);
            System.out.println("The total number of digits that starts with 2 is: " + counter2);
            System.out.println("The total number of digits that starts with 3 is: " + counter3);
            System.out.println("The total number of digits that starts with 4 is: " + counter4);
            System.out.println("The total number of digits that starts with 5 is: " + counter5);
            System.out.println("The total number of digits that starts with 6 is: " + counter6);
            System.out.println("The total number of digits that starts with 7 is: " + counter7);
            System.out.println("The total number of digits that starts with 8 is: " + counter8);
            System.out.println("The total number of digits that starts with 9 is: " + counter9);
            Percentage(counter1, counter2, counter3, counter4, counter5, counter6, counter7, counter8, counter9);


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
            
        }
        
    }

    public static void Percentage(int num1, int num2, int num3, int num4, int num5, int num6, int num7, int num8, int num9){
        
        int sum = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9;
        System.out.println(sum);

        double percentage1 = (num1*100)/sum;
        double percentage2 = (num2*100)/sum;
        double percentage3 = (num3*100)/sum;
        double percentage4 = (num4*100)/sum;
        double percentage5 = (num5*100)/sum;
        double percentage6 = (num6*100)/sum;
        double percentage7 = (num7*100)/sum;
        double percentage8 = (num8*100)/sum;
        double percentage9 = (num9*100)/sum;
        System.out.println("The percentage of digits that start with 1 is: " + percentage1 + "%");
        System.out.println("The percentage of digits that start with 2 is: " + percentage2 + "%");
        System.out.println("The percentage of digits that start with 3 is: " + percentage3 + "%");
        System.out.println("The percentage of digits that start with 4 is: " + percentage4 + "%");
        System.out.println("The percentage of digits that start with 5 is: " + percentage5 + "%");
        System.out.println("The percentage of digits that start with 6 is: " + percentage6 + "%");
        System.out.println("The percentage of digits that start with 7 is: " + percentage7 + "%");
        System.out.println("The percentage of digits that start with 8 is: " + percentage8 + "%");
        System.out.println("The percentage of digits that start with 9 is: " + percentage9 + "%");
    }
        
}
