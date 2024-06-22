package java_11._5_CollectionToArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author atquil
 */
public class CollectionToArray {
    public static void main(String[] args) {

        List<String> names = List.of("Atquil","Atul","Anand");


        //Convert to Array (fixed size)
        String[] namesArrBeforeJava11 = names.toArray(new String[names.size()]);		//Before Java 11
        String[] namesArray = names.toArray(String[]::new);

        Arrays.stream(namesArrBeforeJava11).forEach(System.out::println);
        System.out.println("-----------");
        Arrays.stream(namesArray).forEach(System.out::println);
    }
}
