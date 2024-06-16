package java_8._4_Optional_Class;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author atquil
 */
public class OptionalClass {
    public static void main(String[] args) {
        List<String> someString = Arrays.asList("A","B","C");

        Optional<List<String>> stringsValues = Optional.of(someString);

        System.out.println(stringsValues.isEmpty());
        System.out.println(stringsValues.isPresent());
        System.out.println(stringsValues.get());
        //It returns an empty Optional object. No value is present for this Optional.
        System.out.println(stringsValues.isEmpty());
        //It returns the hash code value of the present value, if any, or returns 0 (zero) if no value is present.
        System.out.println(stringsValues.hashCode());
        //It returns the contained value, if present, otherwise throw an exception to be created by the provided supplier.
        System.out.println(stringsValues.orElseThrow(IllegalArgumentException::new));
        //It returns the value if present, otherwise returns other.
        System.out.println(stringsValues.orElse(List.of("Some value")));
        //It returns a non-empty string representation of this Optional suitable for debugging
        System.out.println(stringsValues.toString());

        //Method
        someString.stream().filter(s -> s.contains("A")).forEach(System.out::println);


    }
}
