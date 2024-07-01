package java_21._4_StringImprovements;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author atquil
 */
public class _2_StringMethods {
    public static void main(String[] args) {
        String string = "the red brown fox jumps over the lazy dog";

        System.out.println("Finding index of red in string:"+string.indexOf("red",1,10));

        String[] parts = string.splitWithDelimiters(" ", 2);
        Arrays.stream(parts).forEach(System.out::println);
        System.out.println(String.join("", parts));
    }
}
