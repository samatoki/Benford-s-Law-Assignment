/*
 * Name: Yuyang Liu, Jeffrey Lin
 * Date: May 5
 * Teacher: Mr. Ho
 * Description:
*/

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BenfordsLaw{
    public static void main(String[] args) throws FileNotFoundException{
        // Initialize variables
        Scanner customer = new Scanner(System.in);
        String userInput, reportSalesOption, checkFraudOption, exitCondition;
        reportSalesOption = "3";
        checkFraudOption = "4";
        exitCondition = "9";

        // Allow users to reinput and choose between options provided
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

    /*
     * Description: Print out the main menu
     * 
     * @return - list of options
     * */
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("3. Report on total Sales\n")
        .concat("4. Check for fraud in sales data\n")
        .concat("9. Quit\n")
        );
    }

    /*
     * Description: Load chosen file into the system
     *              & Output every single piece of info to read
     * 
     * @param userInput - a string used to determine whether to simply print the data or analysis it
     * @return - print the whole file in the terminal, in the form of "Postal Code: \n" + "sales: "
     * */
    public static void loadFile(String userInput) throws FileNotFoundException{
        // Open File
        Scanner sc = new Scanner(System.in);
        System.out.println("Type in the name of the file to read from:");
        String fileName = sc.nextLine();

        // Initialize a variable used to read the file line by line
        String line = "";
        
        // Initialize an int array to store the # of first digits (find the distribution of first digit of sales)
        int[] counters = new int[10];

        // Loading the file into the program
        FileReader saleFile = new FileReader(fileName);
        
        try {
            // Read the file by creating Scanner instance to read the file in the java
            BufferedReader br = new BufferedReader(saleFile);

            // If there are characters in the next line, the loop will continue on
            while((line = br.readLine()) != null){
                // Use .split to separate postal codes & sales
                String[] dataCollection = line.split(",");

                // Print postal codes & sales line by line
                if(userInput.equals("3")){
                    System.out.println("postal code: " + dataCollection[0]);
                    System.out.println("sales: " + dataCollection[1]);
                }

                // Calculate the number of first digits (1-9) and store them into counters[] array
                for(int i = 1; i <= 9; i++){
                    if (Character.getNumericValue(dataCollection[1].charAt(0)) == i){
                        counters[i] += 1;
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }   // catch any errors

        // Call the fraudCheck mehtod to validate the sales
        if(userInput.equals("4")){
            fraudCheck(counters);
        }
    }

    /*
     * Description: Calculate each digits' frequency and validate the sales data
     * 
     * @param counters - an int array that stores the num of first digits (1-9)
     * @return - result for possible accounting fraud check
     * */
    public static void fraudCheck(int[] counters){
        int total = 0;
        double[] frequency = new double[10];
        Scanner choice = new Scanner(System.in);

        for(int i = 1; i <= 9; i++){
            total += counters[i];
        }
        
        for(int i = 1; i <= 9; i++){
            frequency[i] = (counters[i]*100) / total;
        }

        if((frequency[1] > 29.0) && (frequency[1] < 32.0)){
            System.out.println("The data indicates that fraud likely did not occur.");
        }
        else{
            System.out.println("The data indicates that fraud is highly likely to have occurred.");
        }

        System.out.println("To check the result file, enter 'f'\n"
        .concat("To check the frequency chart, enter 'c'")
        );
        String reportCheck = choice.nextLine();
        if(reportCheck.equals("f")){
            String filepath = "results.csv";
            saveRecord(frequency, filepath);
        }
        else if(reportCheck.equals("c")){
            reportResults(frequency);
        }
    }

    public static void saveRecord(double[] frequency, String filepath){
        String percentage = "%";
        String num = "Number";
        
        try{
            FileWriter fw = new FileWriter(filepath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(num + " | " + percentage);
            
            for(int i = 1; i < frequency.length; i++){
                pw.println(i + " | " + frequency[i]);
            }
           
            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null, " Record Saved");
        }catch(Exception E){
            JOptionPane.showMessageDialog(null, " Record not Saved");
        }
    }

    /*
     * Description: Print a bar chart to visualize the distribution of first digits
     * 
     * @param frequency - an double array that stores the percentage of each first digit (1-9)
     * @return - a bar chart which shows first digits distribution
     * */
    public static void reportResults(double[] frequency){
        // Initialize the Stage
        Stage stage = new Stage();

        // Creating X and Y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Adding labels for the axes
        xAxis.setLabel("Digit");
        yAxis.setLabel("Percent(%)");

        // Creating a Bar Chart
        BarChart<String, Number> firstDigitDistribution = new BarChart<>(xAxis, yAxis);
        firstDigitDistribution.setTitle("Distribution of First Digit");

        // Preparing data for the bar chart
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("1 = " + frequency[1]);
        series1.getData().add(new XYChart.Data<>("1", frequency[1]));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series1.setName("2 = " + frequency[2]);
        series1.getData().add(new XYChart.Data<>("2", frequency[2]));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series1.setName("3 = " + frequency[3]);
        series1.getData().add(new XYChart.Data<>("3", frequency[3]));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series1.setName("4 = " + frequency[4]);
        series1.getData().add(new XYChart.Data<>("4", frequency[4]));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series1.setName("5 = " + frequency[5]);
        series1.getData().add(new XYChart.Data<>("5", frequency[5]));

        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series1.setName("6 = " + frequency[6]);
        series1.getData().add(new XYChart.Data<>("6", frequency[6]));

        XYChart.Series<String, Number> series7 = new XYChart.Series<>();
        series1.setName("7 = " + frequency[7]);
        series1.getData().add(new XYChart.Data<>("7", frequency[7]));

        XYChart.Series<String, Number> series8 = new XYChart.Series<>();
        series1.setName("8 = " + frequency[8]);
        series1.getData().add(new XYChart.Data<>("8", frequency[8]));

        XYChart.Series<String, Number> series9 = new XYChart.Series<>();
        series1.setName("9 = " + frequency[9]);
        series1.getData().add(new XYChart.Data<>("9", frequency[9]));

        // Setting the data to bar chart
        firstDigitDistribution.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8, series9);

        // Creating a Group object
        Group root = new Group(firstDigitDistribution);

        // Creating a stack pane to hold the chart
        StackPane pane = new StackPane(firstDigitDistribution);

        //Setting the scene
        Scene scene = new Scene(pane, 600, 400);
        stage.setTitle("Bar Chart Of First Digit Distribution");
        stage.setScene(scene);
        stage.show();
    }
}