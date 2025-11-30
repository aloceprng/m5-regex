package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum length.
     */
    public static boolean checkForPassword(String str, int minLength) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}$";
        return Pattern.matches(regex, str);
    }


    /**
     * Returns a list of email addresses ending with:
     * - @utoronto.ca
     * - @mail.utoronto.ca
     * With at least 1 char before '@'
     */
    public static List<String> extractEmails(String str) {
        final List<String> result = new ArrayList<>();

        if (str == null) {
            return result;  // return empty list instead of crashing
        }

        // One or more non-@ chars before "@"
        // Optional "mail." before utoronto.ca
        final Pattern pattern = Pattern.compile("[^\\s@]+@(mail\\.)?utoronto\\.ca");
        final Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }


    /**
     * Returns true if the string contains the same capital letter twice.
     * Example: "Amazing Apple" -> true (A…A)
     */
    public static boolean checkForDoubles(String str) {
        if (str == null) {
            return false; // return false if input is null
        }

        // Look for any capital letter that appears again later
        // ([A-Z])     -> capture a capital
        // .*          -> anything in between
        // \1          -> the same capital again
        return str.matches(".*([A-Z]).*\\1.*");
    }

}
