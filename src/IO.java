import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {
    private IO(){};

    /*
     * Skriver över personobjekt till en .ser fil
     */
    public static void serializeToFile(File file, List<Customer> customers) throws IOException{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(customers);
            oos.close();
    }
    /*
     * Läser personobjekt from .ser fil
     */
    @SuppressWarnings("unchecked")
    public static List<Customer> deserializeFromFile(File file) throws IOException, ClassNotFoundException {
        List<Customer> personsFromFile;
        if (!file.exists()) {
            // Handle the case where the file doesn't exist, e.g., create a new list
            personsFromFile = new ArrayList<>();
        } else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            personsFromFile = (List<Customer>) ois.readObject();
            ois.close();
        }
        return personsFromFile;
    }

}
