import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Puzzle2 {
    public static void main(String[] args) throws Exception {

        //Read the whole input as a String
        Scanner scanner = new Scanner(Paths.get("Day3/input.txt"), StandardCharsets.UTF_8.name());
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();

        System.out.println("Content length: " + content.length());

        List<Integer> doIndexes = returnDoIndexes(content);
        List<Integer> dontIndexes = returnDontIndexes(content);

        //Check if the last instruction is don't(). If it is, remove the ending
        if (doIndexes.get(doIndexes.size()-1) < dontIndexes.get(dontIndexes.size()-1)) {
            StringBuilder removeEndContent = new StringBuilder(content);
            removeEndContent.setLength(dontIndexes.get(dontIndexes.size()-1));
            String removeEndContentString = removeEndContent.toString();
            
            removeEndContentString = removeEndContentString.replaceAll("(?s)don't\\(\\).*?do\\(\\)", "");
            Integer mulAmount = mulAmount(removeEndContentString);
            System.out.println("Mul amount: " + mulAmount);
        }

        else {
            String trimmedContent = content.replaceAll("(?s)don't\\(\\).*?do\\(\\)", "");
            Integer mulAmount = mulAmount(trimmedContent);
            System.out.println("Mul amount: " + mulAmount);
        }

    }

    public static List<Integer> returnDontIndexes(String content) {
        ArrayList<Integer> dontIndexes = new ArrayList<>();

        Pattern patternDont = Pattern.compile("don't()");
        Matcher matcherDont = patternDont.matcher(content);

        while (matcherDont.find()) {
            dontIndexes.add(matcherDont.end());
        }

        return dontIndexes;
    }

    public static List<Integer> returnDoIndexes(String content) {
        ArrayList<Integer> doIndexes = new ArrayList<>();

        Pattern patternDo = Pattern.compile("do()");
        Matcher matcherDo = patternDo.matcher(content);

        while (matcherDo.find()) {
            doIndexes.add(matcherDo.end());
        }

        return doIndexes;
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
