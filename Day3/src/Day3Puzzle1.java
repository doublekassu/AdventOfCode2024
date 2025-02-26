import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Puzzle1 {
    public static void main(String[] args) throws Exception {

        //Read the whole input as a String
        Scanner scanner = new Scanner(Paths.get("Day3/input.txt"), StandardCharsets.UTF_8.name());
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();

        System.out.println(content);

        Integer mulAmount = mulAmount(content);
        System.out.println("Mul amount: " + mulAmount);
    }

    //Search for mul instances in the input.
    public static Integer mulAmount(String input) {
        
        String number1 = "\\d{1,3}";
        String number2 = "\\d{1,3}";
        Integer sumOfMultiplies = 0;

        //Search for correct syntax muls with regex
        Pattern pattern = Pattern.compile("mul\\(" + number1 + "," + number2 + "\\)");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {

            //Of the correct syntax muls, search for digits and add them to a list
            String matchedText = matcher.group();
            Pattern pattern2 = Pattern.compile("\\d+");
            Matcher matcher2 = pattern2.matcher(matchedText);

            ArrayList<Integer> numbersInString = new ArrayList<>();
            while (matcher2.find()) {
                numbersInString.add(Integer.parseInt((matcher2.group())));
            }

            //Since the length of the list is always 2, multiply the the numbers together and add the result to sumOfMultiplies
            Integer multiply = numbersInString.get(0) * numbersInString.get(1);
            sumOfMultiplies += multiply;
        }
        return sumOfMultiplies;
    }
}
