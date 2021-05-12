/*
 * Name: Yuyang Liu, Jeffery Lin
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
        reader.close();
    }
}