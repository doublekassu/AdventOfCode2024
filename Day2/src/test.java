import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<List<Integer>> listOfRows = addIntegersToListFromFile("input.txt");

        for (List<Integer> row : listOfRows) {
            System.out.println(row);
        }
    }

    public static ArrayList<List<Integer>> addIntegersToListFromFile(String filePath) throws FileNotFoundException {
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
