import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> listOfLists = divideInputIntoTwoLists("input.txt");
        ArrayList<Integer> leftList = listOfLists.get(0);
        ArrayList<Integer> rightList = listOfLists.get(1);

        //Sort both lists to ascending order
        Collections.sort(leftList);
        Collections.sort(rightList);

        HashMap<Integer, Integer> similarityHashMap = leftListAppearanceInRightList(leftList, rightList);
        Integer similarityScore = calculateSimilarityScore(similarityHashMap);

        System.out.println("Similarity score between the lists is: " + similarityScore);
    }

    //PUZZLE2 METHODS

    static Integer calculateSimilarityScore(HashMap<Integer, Integer> hashMap) {
        Integer similarityScore = 0;

        //Iterate the keys (list numbers) in the HashMap. HashMap get (value) is the amount of appearances so the similarityscore can be calculated
        for (int number : hashMap.keySet()) {
            System.out.println("Number " + number + " was in the list " + hashMap.get(number) + " times");
            similarityScore += number * hashMap.get(number);
        }

        return similarityScore;
    }

    static HashMap<Integer, Integer> leftListAppearanceInRightList(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
        HashMap<Integer, Integer> similarityHashMap = new HashMap<>();

        for (int i=0; i<leftList.size(); i++) {
            for (int y=0; y<rightList.size(); y++) {
                if (leftList.get(i).equals(rightList.get(y))) {

                    //If right list has value of left, put it to HashMap as key. HasMap value is the value of the key + 1 (1st 0+1=1, 2nd 1+1=2). Must use getOrDefault to add the first appearance (cant get a value that's non-existent)
                    similarityHashMap.put(rightList.get(y), similarityHashMap.getOrDefault(rightList.get(y), 0) + 1);
                }
            }

        }
        
        return similarityHashMap;
    }
    
    //PUZZLE1 METHODS
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
