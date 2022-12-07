import java.util.Collections;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(calc(in.nextLine()));
    }

    public static String calc(String input) throws Exception {
        Matcher arabicMatcher = Pattern.compile("([1-9]|10)\\s([*/+-])\\s([1-9]|10)").matcher(input);
        Matcher romanMatcher = Pattern.compile("([I]{1,3}|[I][XV]|[V][I]{0,3}|[X])\\s([*/+-])\\s([I]{1,3}|[I][XV]|[V][I]{0,3}|[X])").matcher(input);

        if (arabicMatcher.matches()) {
            int a, b, res;
            switch (arabicMatcher.group(2)) {
                case "+":
                    a = Integer.parseInt(arabicMatcher.group(1));
                    b = Integer.parseInt(arabicMatcher.group(3));
                    res = a + b;
                    break;
                case "-":
                    a = Integer.parseInt(arabicMatcher.group(1));
                    b = Integer.parseInt(arabicMatcher.group(3));
                    res = a - b;
                    break;
                case "*":
                    a = Integer.parseInt(arabicMatcher.group(1));
                    b = Integer.parseInt(arabicMatcher.group(3));
                    res = a * b;
                    break;
                default:
                    a = Integer.parseInt(arabicMatcher.group(1));
                    b = Integer.parseInt(arabicMatcher.group(3));
                    res = a / b;
            }
            return String.valueOf(res);
        } else if (romanMatcher.matches()) {
            String a, b, res;
            switch (romanMatcher.group(2)) {
                case "+":
                    a = romanMatcher.group(1);
                    b = romanMatcher.group(3);
                    res = RomanNumbers.plus(a, b);
                    break;
                case "-":
                    a = romanMatcher.group(1);
                    b = romanMatcher.group(3);
                    res = RomanNumbers.minus(a, b);
                    break;
                case "*":
                    a = romanMatcher.group(1);
                    b = romanMatcher.group(3);
                    res = RomanNumbers.multiply(a, b);
                    break;
                default:
                    a = romanMatcher.group(1);
                    b = romanMatcher.group(3);
                    res = RomanNumbers.divide(a, b);
            }
            return res;
        } else {
            throw new Exception();
        }

    }


}

class RomanNumbers {
    private static int romanToArabic(String romanNum) throws Exception {
        int res;
        switch (romanNum) {
            case "I":
                res = 1;
                break;
            case "II":
                res = 2;
                break;
            case "III":
                res = 3;
                break;
            case "IV":
                res = 4;
                break;
            case "V":
                res = 5;
                break;
            case "VI":
                res = 6;
                break;
            case "VII":
                res = 7;
                break;
            case "VIII":
                res = 8;
                break;
            case "IX":
                res = 9;
                break;
            case "X":
                res = 10;
                break;
            default:
                throw new Exception();
        }
        return res;
    }

    private static String arabicToRoman(int arabicNum) throws Exception {
        if (arabicNum <= 0)
            throw new Exception();
        StringBuilder result = new StringBuilder();
        for (Integer key : units.descendingKeySet()) {
            while (arabicNum >= key) {
                arabicNum -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;

    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }


    public static String plus(String romanNum1, String romanNum2) throws Exception {
        int a = romanToArabic(romanNum1);
        int b = romanToArabic(romanNum2);
        int res = a + b;
        return arabicToRoman(res);
    }

    public static String minus(String romanNum1, String romanNum2) throws Exception {
        int a = romanToArabic(romanNum1);
        int b = romanToArabic(romanNum2);
        int res = a - b;
        if (res < 1)
            throw new Exception();
        return arabicToRoman(res);
    }

    public static String multiply(String romanNum1, String romanNum2) throws Exception {
        int a = romanToArabic(romanNum1);
        int b = romanToArabic(romanNum2);
        int res = a * b;
        return arabicToRoman(res);
    }

    public static String divide(String romanNum1, String romanNum2) throws Exception {
        int a = romanToArabic(romanNum1);
        int b = romanToArabic(romanNum2);
        int res = a / b;
        if (res < 1)
            throw new Exception();
        return arabicToRoman(res);
    }
}
