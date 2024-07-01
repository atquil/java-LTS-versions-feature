package java_21._5_SequencedCollection;

import java.util.List;

/**
 * @author atquil
 */
public class SequencedCollection {
    public static void main(String[] args) {

        List<String> names = List.of("Atquil","Atul","Anand");

        //Earlier to get Last Element --> names.size()-1
        var lastName = names.getLast();
        var firstName = names.getFirst();
        System.out.println("Last Name:"+lastName+" and First name:"+firstName);

        System.out.println("-------------");
        names.reversed();
        names.forEach(System.out::println);

        System.out.println("-------------");
        names.removeLast();
        names.removeFirst();
        names.forEach(System.out::println);






    }
}
