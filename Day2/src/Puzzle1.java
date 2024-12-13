import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle1 {
    public static void main(String[] args) throws FileNotFoundException {
        EaseMethods easeMethods = new EaseMethods();
        ArrayList<List<Integer>> listOfRows = easeMethods.addIntegersToListFromFile("input.txt");

        //Count the safe reports
        Integer safeListCounter = 0;

        for (List<Integer> row : listOfRows) {
            boolean ascendOrDescend = false;
            boolean differ = true;
            //Sort to ascending
            List<Integer> ascendedRow = row.stream().sorted().collect(Collectors.toList());
            //Check if the row is already ascending or descending
            if (row.equals(ascendedRow) || row.equals(ascendedRow.reversed())) {
                ascendOrDescend = true;
            }

            Integer currentValue = ascendedRow.get(0) - 1;
            for (int i=0; i<ascendedRow.size(); i++) {
                //If current value's and next value's difference is more than 3 or current value is the same as the next one, set differ to false
                if (!((ascendedRow.get(i) - currentValue) <= 3) || ascendedRow.get(i).equals(currentValue)) {
                    differ = false;
                }
                currentValue = ascendedRow.get(i);
            }
            
            //If both terms are true, add 1 to the safeListCounter
            if (differ && ascendOrDescend) {
                safeListCounter += 1;
            }
        }
        System.out.println("Total amount of reports: " + listOfRows.size());
        System.out.println("Total amount of safe reports (the correct answer): " + safeListCounter);
    }
}
