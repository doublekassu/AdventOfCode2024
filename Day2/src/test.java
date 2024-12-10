import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(15);
        integerList.add(20);
        for (int i = 0; i < integerList.size(); i++) {
            Integer removedValue = integerList.remove(i); // Remove value at index i
            System.out.println("Removed: " + removedValue);

            // Access the value now at index i
            if (i < integerList.size()) { // Avoid IndexOutOfBoundsException
                System.out.println("Value at current index after removal: " + integerList.get(i));
            }

            // Add the removed value back to its original position
            integerList.add(i, removedValue);
        }
    }
}
