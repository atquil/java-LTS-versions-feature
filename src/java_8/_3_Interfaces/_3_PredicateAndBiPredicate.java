package java_8._3_Interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author atquil
 */


public class _3_PredicateAndBiPredicate {
    public static void main(String[] args) {


        //IsSum Even.
        BiPredicate<Integer, Integer> isSumEven = (a, b) -> (a + b) % 2 == 0;
        boolean result1 = isSumEven.test(10, 20);
        System.out.println("isSumEven"+result1);

        //Is Sum greater then 20
        BiPredicate<Integer, Integer> isSumGreater = (a, b) -> (a + b) > 10;
        boolean result2 = isSumEven.test(10, 20);
        System.out.println("isSumGreaterThen10"+result2);


        //Chaining with and
        BiPredicate<Integer, Integer> combined = isSumEven.and(isSumGreater);
        boolean result3 = combined.test(10, 20);
        System.out.println("isSumEvenAndGreaterThen10"+result3);

        //Chaining with or
        BiPredicate<Integer, Integer> only = isSumEven.or(isSumGreater);
        boolean result4 = only .test(1, 20);
        System.out.println("isSumEvenOrGreaterThen10"+result4);

        //With Streams
        List<String> names = Arrays.asList("Atquil", "Atul", "Anand");
        BiPredicate<String, Integer> isNameLengthGreaterThan = (name, length) -> name.length() > length;
        List<String> longNames = names.stream()
                .filter(name -> isNameLengthGreaterThan.test(name, 5))
                .collect(Collectors.toList());
        System.out.println("Names with length greater than 5 characters: " + longNames);
    }
}
