
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Arrays;

public class PizzaSolution {

    public static Pizza [] pizzas = new Pizza[500];

    public static void main(String[] args) {
        int i = 0;
        String fileName = "../data/b_little_bit_of_everything.in";

        int number_of_pizzas;
        int teams_of_two;
        int teams_of_three;
        int teams_of_four;

        File file = new File(fileName);

		try
		{
			Scanner inputStream = new Scanner(file);

            String order_description = inputStream.nextLine();
            String [] order_details = order_description.split(" ");

            //these are the pizza order details 
            number_of_pizzas = Integer.parseInt(order_details[0]);
            teams_of_two = Integer.parseInt(order_details[1]);
            teams_of_three = Integer.parseInt(order_details[2]);
            teams_of_four = Integer.parseInt(order_details[3]);

			while(inputStream.hasNext())
			{
				String data = inputStream.nextLine();

                String [] pizza_details = data.split(" ",2);

                int numberOfIngredients = Integer.parseInt(pizza_details[0]);
                String ingredients = pizza_details[1];
                pizzas[i] = new Pizza(i, numberOfIngredients, ingredients); 
		
				i++;
			}
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        Arrays.sort(pizzas);

        for(int k = 0; k < pizzas.length;k++){
            System.out.println(pizzas[k]);
        }
    }
    
}
