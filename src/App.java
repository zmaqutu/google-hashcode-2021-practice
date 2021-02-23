import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class App {

    static int TWO_PERSON_TEAM = 2;
    static int THREE_PERSON_TEAM = 3;
    static int FOUR_PERSON_TEAM = 4;
    static String FOLDER = "\\src\\data\\";
    // static String FILE_PATH = System.getProperty("user.dir") + FOLDER;
    static String FILE_PATH = "C:\\Users\\mgngm\\Desktop\\AWS\\google-hashcode-2021-practice\\src\\data\\";
    static String FILE_NAME = "a_example";

    public static void main(String[] args) throws Exception {
        // System.out.println(FILE_PATH);

        // List<String> test = new ArrayList<String>();
        // test.add("one");
        // test.add("two");
        // test.add("three");

        // String joined2 = String.join(" ", test );

        // System.out.println(joined2);

        AvailablePizza pizza = setDataFileVariables();

        deliverPizza(pizza);

    }

    static void deliverPizza(AvailablePizza pizza) {
        // TODO: Send out pizzas and minus from the available pizza

        int M = pizza.getNumOfPizzaAvailable();
        int T2 = (pizza.getTeamOf2() * TWO_PERSON_TEAM);
        int T3 = (pizza.getTeamOf3() * THREE_PERSON_TEAM);
        int T4 = (pizza.getTeamOf4() * FOUR_PERSON_TEAM);
        var persons = List.of(pizza.getIngredients());

        List<Ingredients> ing = pizza.getIngredients();

        // TODO check if the ingredient already exists

        // File output variables
        int numberOfPizzaDelivered = 0; // Done
        // List<String> deliveredPizzas = new ArrayList<>();
        List<String> deliveredPizzas = new ArrayList<String>();
        List<Integer> teamOf = new ArrayList<>();

        // Send the deliveries
        for (int i = 0; i < M; i++) {

            // Not sure what this is ???
            // TODO - NOT SURE - This function reduce the i with -1 to keep it within the
            // range
            if (i != 0)
                i--;

            // Clear the temporary list
            List<String> pizzaIndex = new ArrayList<>();

            if (pizza.getTeamOf2() != 0) {
                // Send it to a team of 2
                for (int j = 0; j < TWO_PERSON_TEAM; j++) {
                    if (pizzaIndex.size() > 0) {
                        pizzaIndex.set(0, String.valueOf(TWO_PERSON_TEAM));
                    } else {
                        pizzaIndex.add(0, String.valueOf(TWO_PERSON_TEAM));
                    }
                    pizzaIndex.add(String.valueOf(ing.get(i + j).index));
                }

                teamOf.add(TWO_PERSON_TEAM);
                deliveredPizzas.add(String.join(" ", pizzaIndex));
                numberOfPizzaDelivered += 1;
                i += TWO_PERSON_TEAM;
                pizza.setTeamOf2(pizza.getTeamOf2() - 1);

            } else if (pizza.getTeamOf3() != 0) {
                // Send it to the team of 3
                for (int j = 0; j < THREE_PERSON_TEAM; j++) {
                    if (pizzaIndex.size() > 0) {
                        pizzaIndex.set(0, String.valueOf(THREE_PERSON_TEAM));
                    } else {
                        pizzaIndex.add(0, String.valueOf(THREE_PERSON_TEAM));
                    }
                    pizzaIndex.add(String.valueOf(ing.get(i + j).index));
                }

                deliveredPizzas.add(String.join(" ", pizzaIndex));
                numberOfPizzaDelivered += 1;
                pizza.setTeamOf3(pizza.getTeamOf3() - 1);
                i += THREE_PERSON_TEAM;

            } else if (pizza.getTeamOf4() != 0) {
                // Send it to the team of 4
                for (int j = 0; j < FOUR_PERSON_TEAM; j++) {
                    if (pizzaIndex.size() > 0) {
                        pizzaIndex.set(0, String.valueOf(FOUR_PERSON_TEAM));
                    } else {
                        pizzaIndex.add(0, String.valueOf(FOUR_PERSON_TEAM));
                    }
                    pizzaIndex.add(String.valueOf(ing.get(i + j).index));
                }

                deliveredPizzas.add(String.join(" ", pizzaIndex));
                numberOfPizzaDelivered += 1;
                pizza.setTeamOf4(pizza.getTeamOf4() - 1);
                i += FOUR_PERSON_TEAM;
            }
        }

        writeOutputResults(numberOfPizzaDelivered, deliveredPizzas);

        // TODO First calculate which teams will get a pizza
        // TODO deliver ingredients while removing them from the list of ingredients
        // TODO Pass the data to the output function
        // TODO Create a function that will deliver unique pizzas
    }

    static List<String> getContextOfFile() {

        Path filePath = Paths.get(FILE_PATH + FILE_NAME);
        Charset charset = StandardCharsets.UTF_8;

        try {
            return Files.readAllLines(filePath, charset);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.format("I/O error: %s%n", e);
            e.printStackTrace();
            return null;
        }

    }

    static AvailablePizza setDataFileVariables() {

        List<String> lines = getContextOfFile();

        int availPizz, T2, T3, T4 = 0;

        String x = lines.get(0);
        String[] nums = x.split(" ");

        // TODO Check for nulls
        availPizz = Integer.parseInt(nums[0]);
        T2 = Integer.parseInt(nums[1]);
        T3 = Integer.parseInt(nums[2]);
        T4 = Integer.parseInt(nums[3]);

        List<Ingredients> ingredients = new ArrayList<Ingredients>();

        // TODO - BRB
        for (int i = 1; i < lines.size(); i++) {

            String[] rec = lines.get(i).split(" ");
            String[] modRec = Arrays.copyOfRange(rec, 1, rec.length);

            Ingredients ing = new Ingredients((i - 1), Integer.parseInt(rec[0]), Arrays.asList(modRec));
            ingredients.add(ing);
        }

        AvailablePizza availablePizza = new AvailablePizza(availPizz, T2, T3, T4, ingredients);
        return availablePizza;

    }

    static void writeOutputResults(int numberOfPizzaDelivered, List delList) {
        System.out.format("Number of Pizza Delivered: %s%n", numberOfPizzaDelivered);
        // TODO - Write the outpu to the file
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH + FILE_NAME + "_results.txt");
            myWriter.write(numberOfPizzaDelivered + "\n");

            for (int i = 0; i < delList.size(); i++) {
                System.out.println(delList.get(i).toString());
                myWriter.write(delList.get(i).toString() + "\n");
            }
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
        int index;
        int numberOfIngredients;
        List<String> ingredients;

        // Getters
        public int getIndex() {
            return index;
        }

        public int getNumberOfIngredients() {
            return numberOfIngredients;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        // Setters
        public void setIndex(int index) {
            this.index = index;
        }

        public void setNumberOfIngredients(int numberOfIngredients) {
            this.numberOfIngredients = numberOfIngredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public Ingredients(int index, int numberOfIngredients, List<String> ingredients) {
            this.index = index;
            this.ingredients = ingredients;
            this.numberOfIngredients = numberOfIngredients;
        }
    }
}
