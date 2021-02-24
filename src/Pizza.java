

//this class represents a single pizza
public class Pizza implements Comparable<Pizza> {

    int pizza_number;
    int no_of_ingridients;
    String ingredients;

    Pizza(int pizza_number, int no_of_ingridients, String ingredients){
        super();
        this.pizza_number = pizza_number;
        this.no_of_ingridients = no_of_ingridients;
        this.ingredients = ingredients;
    }

    int getPizzaNumber(){
        return pizza_number;
    }
    int getNumberOfIngredients(){
        return no_of_ingridients;
    }
    String getIngredients(){
        return ingredients;
    }
    public String toString(){
        return String.format(no_of_ingridients + " " + ingredients );   
    }

    @Override
    public int compareTo(Pizza comparePizza){

        int compareNumberOfIngredients = ( (Pizza) comparePizza).getNumberOfIngredients();

        return compareNumberOfIngredients - this.no_of_ingridients;
    }
}
