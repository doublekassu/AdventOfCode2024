import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Puzzle1 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> listOfLists = divideInputIntoTwoLists("input.txt");
        ArrayList<Integer> leftList = listOfLists.get(0);
        ArrayList<Integer> rightList = listOfLists.get(1);

        //Sort both lists to ascending order
        Collections.sort(leftList);
        Collections.sort(rightList);
     
        Integer totalDifference = divideInputIntoTwoLists(leftList, rightList);

        System.out.println("Total distance difference between lists: " + totalDifference);
    }

    static Integer divideInputIntoTwoLists(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
        Integer totalDifference = 0;
        for (int i=0; i<leftList.size(); i++) {
            
            //Positive value of the distance between the values
            Integer positiveSubtraction = Math.abs(leftList.get(i)-rightList.get(i));
            totalDifference += positiveSubtraction;
        }

        return totalDifference;
    }

    static ArrayList<ArrayList<Integer>> divideInputIntoTwoLists(String filePath) throws FileNotFoundException {
        File input = new File(filePath);
        Scanner scanner = new Scanner(input);
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        Integer oddEven = 0;

        //If current value is added to left list, add next one to the right list
        while (scanner.hasNextInt()) {
            if (oddEven.equals(0)) {
                leftList.add(scanner.nextInt());
                oddEven = 1;
            }
            else if (oddEven.equals(1)) {
                rightList.add(scanner.nextInt());
                oddEven = 0;
            }
        }
        
        scanner.close();

        ArrayList<ArrayList<Integer>> listOfDividedLists = new ArrayList<>();

        //0 index=left lists' values, 1 index=right lists' values
        listOfDividedLists.add(leftList);
        listOfDividedLists.add(rightList);

        return listOfDividedLists;
    }
}
