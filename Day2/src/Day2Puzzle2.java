import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day2Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<List<Integer>> listOfRows = addIntegersToListFromFile("input.txt");
        
        //Count the safe reports
        Integer safeListCounter = 0;

        for (List<Integer> row : listOfRows) {
            boolean ascendingOrDescending = checkIfAscendingOrDescending(row);
            boolean differ = checkIfNeighbourNumbersDiffer(row);
            
            //If both terms are true, add 1 to the safeListCounter
            if (differ && ascendingOrDescending) {
                safeListCounter++;
            }

            else {
                if (checkIfValueRemovalChangesResult(row)) {
                    safeListCounter++;
                }
            }
        }

        System.out.println("Total amount of reports: " + listOfRows.size());
        System.out.println("Total amount of safe reports (the correct answer): " + safeListCounter);
    }

    static boolean checkIfValueRemovalChangesResult(List<Integer> row) {
        for (int i=0; i<row.size(); i++) {
            ArrayList<Integer> tempRow = new ArrayList<>(row);

            //Remove a value at a time
            tempRow.remove(i);

            if (checkIfAscendingOrDescending(tempRow) && checkIfNeighbourNumbersDiffer(tempRow)) {
                return true;
            }
        }

        return false;
    }

    static boolean checkIfNeighbourNumbersDiffer(List<Integer> row) {
        List<Integer> ascendedRow = row.stream().sorted().collect(Collectors.toList());

        //Convert list to ascending order for simplicity in logic
        Integer currentValue = ascendedRow.get(0) - 1;
        for (int i=0; i<row.size(); i++) {

            //If current value's and next value's difference is more than 3 or current value is the same as the next one, set differ to false
            if ((ascendedRow.get(i) - currentValue) > 3 || ascendedRow.get(i) - currentValue < 1) {
                return false;
            }
            currentValue = ascendedRow.get(i);
        }

        return true;
    }

    static boolean checkIfAscendingOrDescending(List<Integer> row) {

        //Sort to ascending
        List<Integer> ascendedRow = row.stream().sorted().collect(Collectors.toList());
        List<Integer> descendedRow = ascendedRow.reversed();
        
        //Check if the row is already ascending or descending
        if (row.equals(ascendedRow) || row.equals(descendedRow)) {
            return true;
        }
        return false;
    }
    
    //Use pathing relative to the one code is ran on!
    static ArrayList<List<Integer>> addIntegersToListFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<List<Integer>> listOfRows = new ArrayList<>();
        while (scanner.hasNextLine()) {
            List<Integer> rowNumbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            listOfRows.add(rowNumbers);
        }
        
        scanner.close();
        return listOfRows;
    }
}