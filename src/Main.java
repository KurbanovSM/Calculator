import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String text = scn.nextLine();

        try {
            calculatorProcess(text);
        }
        catch (Exception e) {
            System.out.println("Неверный формат выражения");
        }
    }

    private static void calculatorProcess(String text) throws Exception{
        if (text.matches("\".*\" (\\+|\\-|\\*|\\/) (\".*\"|\\d+)")){
            if (text.matches("\".*\" \\+ \".*\"")) {      // +
                processingPlusSign(text);
            }
            else if (text.matches("\".*\" \\- \".*\"")) { // -
                processingMinusSign(text);
            }
            else if (text.matches("\".*\" \\* \\d+")) {   // *
                String[] intermediateResult = clearingAndSplittingString(text, " \\* ");
                int number = Integer.parseInt(intermediateResult[1]);

                if(number > 10)
                    throw new Exception();

                ProcessingMultiplyingSign(text);
            }
            else if (text.matches("\".*\" \\/ \\d+")) {   // /
                String[] intermediateResult = clearingAndSplittingString(text, " \\/ ");
                int number = Integer.parseInt(intermediateResult[1]);

                if(number > 10)
                    throw new Exception();

                ProcessingDivisionSign(text);
            }
            else throw new Exception();
        }
        else throw new Exception();
    }

    private static String[] clearingAndSplittingString(String text, String conditionalSign){
        text = text.replace("\"", "");
        return text.split(conditionalSign);
    }

    private static void processingPlusSign(String text){
        text = text.replace("\"", "");
        String[] intermediateResult = clearingAndSplittingString(text, " \\+ ");
        String result = "";

        for (String str: intermediateResult){
            result += str;
        }

        ViewResult(result);
    }

    private static void processingMinusSign(String text){
        String[] intermediateResultOne = clearingAndSplittingString(text, " \\- ");
        String[] intermediateResultTo = intermediateResultOne[0].split(intermediateResultOne[1]);
        String result = intermediateResultTo[0];

        ViewResult(result);
    }

    private static void ProcessingMultiplyingSign(String text){
        String[] intermediateResult = clearingAndSplittingString(text, " \\* ");
        int multiplying = Integer.parseInt(intermediateResult[1]);
        String result = "";

        for (int i = 0; i < multiplying; i++) {
            result += intermediateResult[0];
        }

        ViewResult(result);
    }

    private static void ProcessingDivisionSign(String text){
        String[] intermediateResult = clearingAndSplittingString(text, " \\/ ");
        int division = Integer.parseInt(intermediateResult[1]);
        String result = "";

        for (int i = 0; i < division; i++) {
            result += intermediateResult[0].charAt(i);
        }

        ViewResult(result);
    }

    private static void ViewResult(String text){
        String result = "";
        char symbol = '\"';

        result += symbol;

        if (text.length() > 41){
            result = text.substring(0, 40);
            result += "...";
        }
        else {
            result += text;
        }

        result += symbol;

        System.out.println(result);
    }
}