/*
 * Name: Yuyang Liu
 * Date: May 5
 * Teacher: Mr. Ho
 * Description:
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BenfordsLaw{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Open File
        String fileName = "sales.csv";
        //Loading the file into the program
        File saleFile = new File(fileName);
        
        // Read the file by creating Scanner instance to read the file in the java
        Scanner reader = new Scanner(saleFile);

        // Sets the delimiter pattern
        reader.useDelimiter(",");
        
        while (reader.hasNext())
        {
            System.out.print(reader.next());
        }
        
        reader.close();
    }
}