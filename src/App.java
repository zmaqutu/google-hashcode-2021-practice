import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.ArrayList;

public class App {

    static int TWO_PERSON_TEAM = 2;
    static int THREE_PERSON_TEAM = 3;
    static int FOUR_PERSON_TEAM = 4;
    static String FOLDER = "\\src\\data\\";
    static String FILE_PATH = System.getProperty("user.dir") + FOLDER;
    // static String FILE_PATH = "C:\\Users\\mgngm\\Desktop\\AWS\\google-hashcode-2021-practice\\src\\data\\";
    static String FILE_NAME = "a_example";

    public static void main(String[] args) throws Exception {

        AvailablePizza pizza = setDataFileVariables();

        deliverPizza(pizza);

    }

    static void deliverPizza(AvailablePizza pizza) {
        // TODO: Send out pizzas and minus from the available pizza
        System.out.println(pizza);

        int lvar = 0;

        int M = pizza.getNumOfPizzaAvailable();
        int T2 = (pizza.getTeamOf2() * TWO_PERSON_TEAM);
        int T3 = (pizza.getTeamOf3() * THREE_PERSON_TEAM);
        int T4 = (pizza.getTeamOf4() * FOUR_PERSON_TEAM);

        var persons = List.of(pizza.getIngredients());

        // List<Ingredients> byAge = person -> person.ge > 30;

        List<Ingredients> ing = pizza.getIngredients();
        // Stream<Ingredients> myStream = Arrays.stream(ing);

        // TODO check if the ingredient already exists

        // File output variables
        int numberOfPizzaDelivered = 0; // Done
        List<List> deliveredPizzas = new ArrayList<>();
        List<String> pizzaIndex = new ArrayList<>();

        // int total = T2 + T3 + T4;
        // int idx = 0;

        for (int i = 0; i < M; i++) {

            // if (i != 0)
            //     i--;

            if (pizza.getTeamOf2() != 0) {
                // Team - Piza Options

                pizzaIndex.add(String.valueOf(ing.get(i).index));
                pizzaIndex.add(String.valueOf(ing.get(i + 1).index));

                deliveredPizzas.add(pizzaIndex);
                numberOfPizzaDelivered += 1;
                i += TWO_PERSON_TEAM;
                pizza.setTeamOf2(pizza.getTeamOf2() - 1);

            } else if (pizza.getTeamOf3() != 0) {

                // Team - Piza Options
                pizzaIndex.add(String.valueOf(ing.get(i).index));
                pizzaIndex.add(String.valueOf(ing.get(i + 1).index));
                pizzaIndex.add(String.valueOf(ing.get(i + 2).index)); 

                deliveredPizzas.add(pizzaIndex);
                // Default back
                numberOfPizzaDelivered += 1;
                pizza.setTeamOf3(pizza.getTeamOf3() - 1);
                // i += THREE_PERSON_TEAM;
            }
        }

        System.out.println(numberOfPizzaDelivered);

        // TODO First calculate which teams will get a pizza
        // TODO deliver ingredients while removing them from the list of ingredients
        //
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

            Ingredients ing = new Ingredients((i - 1), Integer.parseInt(rec[0]), Arrays.asList(modRec));
            ingredients.add(ing);
        }

        // List<AvailablePizza> availablePizzas = new ArrayList<AvailablePizza>();
        AvailablePizza availablePizza = new AvailablePizza(availPizz, T2, T3, T4, ingredients);
        return availablePizza;

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
