package java_8._6_StringJoiners;

import java.util.StringJoiner;

/**
 * @author atquil
 */
public class StringJoiners {
    public static void main(String[] args) {

        StringJoiner someJoiners = new StringJoiner(",","Names:",": End");

        // We can set default empty value.
        someJoiners.setEmptyValue("Default Value");
        System.out.println(someJoiners);

        // Adding values to StringJoiner
        someJoiners.add("Atul");
        someJoiners.add("Anand");
        someJoiners.add("Atquil");

        System.out.println(someJoiners);
        System.out.println("CharLength: "+someJoiners.length());
        System.out.println("To String: "+someJoiners.toString());
    }
}
