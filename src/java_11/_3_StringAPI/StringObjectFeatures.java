package java_11._3_StringAPI;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author atquil
 */
public class StringObjectFeatures {
    public static void main(String[] args) {

        System.out.println("---------Multiple Lines : lines(), isBlank(), isEmpty(), strip() ---------");
        // Multiple Lines Reading
        String multilineString = "Line1 \n Line2 \n Line3 \n  \nLine5" ;

        // Iterate on lines
        multilineString.lines()
                //Checks for blank and empty and removes Line4
                .filter(line ->!line.isBlank() && !line.isEmpty())
                // Strip
                .map(String::strip)
                .map(String::stripTrailing) // Remove from last
                .map(String::stripLeading)
                .forEach(System.out::println);


        System.out.println("---------Repeat ---------");
        System.out.println("Atquil will be repated 5 times:: "+"atquil".repeat(5));
    }
}
