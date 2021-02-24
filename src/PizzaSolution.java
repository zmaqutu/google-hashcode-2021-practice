
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

		try
		{
			Scanner inputStream = new Scanner(file);

            String order_description = inputStream.nextLine();
            String [] order_details = order_description.split(" ");

            //these are the pizza order details 
            int number_of_pizzas = Integer.parseInt(order_details[0]);
            int teams_of_two = Integer.parseInt(order_details[1]);
            int teams_of_three = Integer.parseInt(order_details[2]);
            int teams_of_four = Integer.parseInt(order_details[3]);

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
        System.out.println(extracted_data[0]);
    }
    
}
