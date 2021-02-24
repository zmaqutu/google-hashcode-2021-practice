
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class PizzaSolution {

    public static String [] extracted_data = new String[300];

    public static void main(String[] args) {
        int i = 0;
        String fileName = "../data/a_example";

        File file = new File(fileName);

		Scanner scan = new Scanner(System.in);

		try
		{
			Scanner inputStream = new Scanner(file);

			while(inputStream.hasNext())
			{
				String data = inputStream.nextLine();

				extracted_data[i] = data;
		
				i++;
			}
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        System.out.println(extracted_data[1]);
    }
    
}
