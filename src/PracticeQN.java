import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

class HashCode {
    public static void readData(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNext()) {
                System.out.println(sc.next());
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Unable to open input file " + fileName);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        readData(args[0]);
        //create a custom object storing configs / restrictions. Expand on them as much as possible
    }
}
