import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.ListDataEvent;

public class App {

    static int TWO_PERSON_TEAM = 2;
    static int THREE_PERSON_TEAM = 3;
    static int FOUR_PERSON_TEAM = 4;
    static String FILE_PATH = "C:\\Users\\mgngm\\Desktop\\AWS\\google-hashcode-2021-practice\\src\\data\\";
    static String FILE_NAME = "a_example";

    public static void main(String[] args) throws Exception {

        // read();

        writeOutputResults();

    }

    static void deliverPizza() {
        // TODO: Send out pizzas and minus from the available pizza
    }

    static void read() {
        Path filePath = Paths.get(FILE_PATH + FILE_NAME);
        Charset charset = StandardCharsets.UTF_8;

        readDataFile(filePath, charset);

    }

    static AvailablePizza readDataFile(Path path, Charset charset) {

        try {
            List<String> lines = Files.readAllLines(path, charset);

            int availPizz = 0, T2 = 0, T3 = 0, T4 = 0;

            String x = lines.get(0);
            String[] nums = x.split(" ");

            // for (int j = 0; j < nums.length; j++) {
            // TODO Check for nulls
            availPizz = Integer.parseInt(nums[0]);
            T2 = Integer.parseInt(nums[1]);
            T3 = Integer.parseInt(nums[2]);
            T4 = Integer.parseInt(nums[3]);
            // }

            List<Ingredients> ingredients = new ArrayList<Ingredients>();

            // TODO - BRB
            for (int i = 1; i < lines.size(); i++) {

                String[] rec = lines.get(i).split(" ");
                String[] modRec = Arrays.copyOfRange(rec, 1, rec.length);

                // Ingredients ing = new Ingredients(numberOfIngredients, ingredients)
                // List<String> ingredients
                Ingredients ing = new Ingredients(Integer.parseInt(rec[0]), Arrays.asList(modRec));
                ingredients.add(ing);
            }

            // List<AvailablePizza> availablePizzas = new ArrayList<AvailablePizza>();
            AvailablePizza availablePizza = new AvailablePizza(availPizz, T2, T3, T4, ingredients);
            return availablePizza;

        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
            ex.printStackTrace();
            return null;
        }

        // TODO Count the number of teams that can add up to M number

        // for (int i = 1; i < list.size()int; i++) {

        // System.out.println(list.get(i));

        // }

        // String pizzaAndTeams = list.get(0).toString();
        // String[] nums = pizzaAndTeams.split(" ");

        // BaseNumbers baseNumbers = new BaseNumbers(nums[0], nums[1], nums[2],
        // nums[3]);

        // int M = Integer.parseInt(baseNumbers.getNumOfPizzaAvailable());

        // int T2 = Integer.parseInt(baseNumbers.getTeamOf2()) * TWO_PERSON_TEAM;
        // int T3 = Integer.parseInt(baseNumbers.getTeamOf3()) * THREE_PERSON_TEAM;
        // int T4 = Integer.parseInt(baseNumbers.getTeamOf4()) * FOUR_PERSON_TEAM;
        // return null;
    }

    static void writeOutputResults() {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH + FILE_NAME + "_results.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static class AvailablePizza {
        int numOfPizzaAvailable;
        int teamOf2;
        int teamOf3;
        int teamOf4;
        List<Ingredients> ingredients;

        // Getters
        public int getNumOfPizzaAvailable() {
            return numOfPizzaAvailable;
        }

        public int getTeamOf2() {
            return teamOf2;
        }

        public int getTeamOf3() {
            return teamOf3;
        }

        public int getTeamOf4() {
            return teamOf4;
        }

        public List<Ingredients> getIngredients() {
            return ingredients;
        }

        // Setters
        public void setNumOfPizzaAvailable(int numOfPizzaAvailable) {
            this.numOfPizzaAvailable = numOfPizzaAvailable;
        }

        public void setTeamOf2(int teamOf2) {
            this.teamOf2 = teamOf2;
        }

        public void setTeamOf3(int teamOf3) {
            this.teamOf3 = teamOf3;
        }

        public void setTeamOf4(int teamOf4) {
            this.teamOf4 = teamOf4;
        }

        public void setIngredients(List<Ingredients> ingredients) {
            this.ingredients = ingredients;
        }

        public AvailablePizza(int numOfPizzaAvailable, int teamOf2, int teamOf3, int teamOf4,
                List<Ingredients> ingredients) {
            this.numOfPizzaAvailable = numOfPizzaAvailable;
            this.teamOf2 = teamOf2;
            this.teamOf3 = teamOf3;
            this.teamOf4 = teamOf4;
            this.ingredients = ingredients;
        }
    }

    static class Ingredients {
        int numberOfIngredients;
        List<String> ingredients;

        // Getters
        public int getNumberOfIngredients() {
            return numberOfIngredients;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        // Setters
        public void setNumberOfIngredients(int numberOfIngredients) {
            this.numberOfIngredients = numberOfIngredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public Ingredients(int numberOfIngredients, List<String> ingredients) {
            this.ingredients = ingredients;
            this.numberOfIngredients = numberOfIngredients;
        }
    }
}
