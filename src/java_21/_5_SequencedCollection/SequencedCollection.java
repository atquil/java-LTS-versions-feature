package java_21._5_SequencedCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atquil
 */
public class SequencedCollection {
    public static void main(String[] args) {

        //List.of() will give immutable list which cannot be modified.
        List<String> names = new ArrayList<>(); // Use ArrayList for mutability
        names.add("Anand");
        names.add("Atul");
        names.add("Atquil");

        //Earlier to get Last Element --> names.size()-1
        var lastName = names.getLast();
        var firstName = names.getFirst();
        System.out.println("Last Name:"+lastName+" and First name:"+firstName);

        System.out.println("-------------");
        names.reversed();
        names.forEach(System.out::println);

        System.out.println("-------------");
        names.removeLast();//Error
        names.removeFirst();
        names.addFirst("Done");
        names.forEach(System.out::println);

    }
}
