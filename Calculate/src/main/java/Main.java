import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String[] ROM_NUM = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final String[] letters = {"I", "IV", "V", "IX", "X", "L", "C", "D", "M"};
    private static final int[] numbers = {1, 4, 5, 9, 10, 50, 100, 500, 1000};
    private static boolean ROM_NUM_FLAG = false;

    public static void main(String[] args) {
        System.out.println("Введите напишите пример: a + b, a - b, a * b, a / b");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));


    }

    public static String calc(String input) {
        String result = "";
        try {
            String text = romanNumeral(input);
            String[] arr = text.split(" ");

            if (interval(text)) {
                switch (arr[1]) {
                    case "*" -> {
                        if (ROM_NUM_FLAG)
                            result = convertIntegerToRoman(parse(arr[0]) * parse(arr[2]));
                        else result = String.valueOf(parse(arr[0]) * parse(arr[2]));
                    }
                    case "/" -> {
                        if (ROM_NUM_FLAG)
                            result = convertIntegerToRoman(parse(arr[0]) / parse(arr[2]));
                        else result = String.valueOf(parse(arr[0]) / parse(arr[2]));
                    }

                    case "+" -> {
                        if (ROM_NUM_FLAG)
                            result = convertIntegerToRoman(parse(arr[0]) + parse(arr[2]));
                        else result = String.valueOf(parse(arr[0]) + parse(arr[2]));
                    }
                    case "-" -> {
                        if (ROM_NUM_FLAG)
                            result = convertIntegerToRoman(parse(arr[0]) - parse(arr[2]));
                        else result = String.valueOf(parse(arr[0]) - parse(arr[2]));
                    }
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex + ": используются одновременно разные системы счисления");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex + ": строка не является математической операцией");
        }
        return result;
    }

    public static int parse(String text) {
        return Integer.parseInt(text);
    }

    public static boolean check(String text) {
        boolean flag = true;
        int count = 0;
        String[] arrString = text.split(" ");

        for (String s : ROM_NUM) {
            if (arrString[0].equals(s))
                count++;
            if (arrString[2].equals(s))
                count++;
        }
        if (count == 1)
            flag = false;

        return flag;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String romanNumeral(String text) {
        checkSize(text);
        String result = text;
        String a = "";
        String b = "";
        String[] arr = text.split(" ");

        if (check(text) && !isDigit(arr[0]) && !isDigit(arr[2])) {

            for (int i = 0; i <= 2; i++) {

                for (int k = 0; k < ROM_NUM.length; k++) {
                    if (arr[i].equals(ROM_NUM[k]) && i == 0)
                        a = String.valueOf(k + 1);
                    if (arr[i].equals(ROM_NUM[k]) && i == 2)
                        b = String.valueOf(k + 1);
                }
                i++;
            }
            ROM_NUM_FLAG = true;
            result = a + " " + arr[1] + " " + b;
        }

        return result;
    }


    public static String convertIntegerToRoman(int number) {

        try {
            if (number < 0)
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println(e + " в римской системе нет отрицательных чисел");
        }

        String romanValue = "";
        int N = number;
        while (N > 0) {
            for (int i = 0; i < numbers.length; i++) {
                if (N < numbers[i]) {
                    N -= numbers[i - 1];
                    romanValue += letters[i - 1];
                    break;
                }
            }
        }
        return romanValue;
    }

    public static int checkSize(String text) {
        int size = text.split(" ").length;
        if (size > 3)
            throw new ArrayIndexOutOfBoundsException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        return size;
    }

    public static boolean interval(String text) {
        boolean flag = false;
        String[] arr = text.split(" ");
        if (parse(arr[0]) > 0 && parse(arr[0]) < 11 &&
                parse(arr[2]) > 0 && parse(arr[2]) < 11) {
            flag = true;
        }
        return flag;
    }
}
