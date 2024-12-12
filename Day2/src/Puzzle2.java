import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
    public static void main(String[] args) {
        //Insert the lists values as a String
        String inputs = "83 86 85 88 89 91 91";
                        
        //Make every row a String list by splitting them from the line switch
        String[] rows = inputs.split("\r\n");
        ArrayList<List<Integer>> listOfLists = new ArrayList<>();
        //Iterate all rows
        for (String row : rows) {
            //Add to an Integer list all the String numbers by splitting the values by the spaces
            List<Integer> rowNumbers = Arrays.stream(row.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            //Add the Integer lists (rows) to a list
            listOfLists.add(rowNumbers);
        }
        //Count the safe reports
        Integer safeListCounter = 0;

        for (List<Integer> row : listOfLists) {
            boolean ascendOrDescend = false;
            boolean differ = true;
            //Sort to ascending
            List<Integer> ascendedRow = row.stream().sorted().collect(Collectors.toList());
            List<Integer> descendedRow = ascendedRow.reversed();
            //Check if the row is already ascending or descending
            
            if (row.equals(ascendedRow) || row.equals(descendedRow)) {
                ascendOrDescend = true;
            }
            
            else {
                for (int i=0; i<row.size(); i++) {
                    Integer removedValue = row.remove(i);
                    List<Integer> ascendedRowInLoop = row.stream().sorted().collect(Collectors.toList());
                    List<Integer> descendedRowInLoop = ascendedRowInLoop.reversed();
                    if (row.equals(ascendedRowInLoop) || row.equals(descendedRowInLoop)) {
                        ascendOrDescend = true;
                    }
                    row.add(i, removedValue);
                }
            }
            
            Integer currentValue = ascendedRow.get(0) - 1;
            for (int i=0; i<ascendedRow.size(); i++) {
                //If current value's and next value's difference is more than 3 or current value is the same as the next one, set differ to false
                if (!((ascendedRow.get(i) - currentValue) <= 3) || ascendedRow.get(i).equals(currentValue)) {
                    differ = false;
                }
                currentValue = ascendedRow.get(i);
            }
            
            if (!differ) {
                //Loop for removing
                System.out.println("Rivi " + row + " tarvitsee differ korjausta!");
                for (int i=0; i<ascendedRow.size(); i++) {
                    differ = true;
                    Integer removedValue = ascendedRow.remove(i);
                    System.out.println("Poistetaan arvo: " + removedValue);
                    System.out.println("Lista poiston jälkeen " + ascendedRow);
                    currentValue = ascendedRow.get(0) - 1;
                    //Loop for checking values
                    
                    for (int k=0; k<ascendedRow.size(); k++) {
                        if (!((ascendedRow.get(k) - currentValue) <= 3) || ascendedRow.get(k).equals(currentValue)) {
                            differ = false;
                        }
                        currentValue = ascendedRow.get(k);
                    }
                    ascendedRow.add(i, removedValue);
                    System.out.println("Lisätään arvo " + removedValue + " indeksiin: " + i);
                    System.out.println("Nyt lista: " + ascendedRow);
                    if (differ) {
                        System.out.println(ascendedRow);
                        break;
                    }
                }
            }
            
            //If both terms are true, add 1 to the safeListCounter
            if (differ && ascendOrDescend) {
                safeListCounter++;
            }
            else {
                System.out.println("Tämä rivi ei ole validi! " + row);
            }
        }
        System.out.println("Total amount of reports: " + listOfLists.size());
        System.out.println("Total amount of safe reports (the correct answer): " + safeListCounter);
    }
}
