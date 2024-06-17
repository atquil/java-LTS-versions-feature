package java_8._5_ForEach_Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author atquil
 */
public class ForEachClass {

    public static void main(String[] args) {
        List<String> someList = Arrays.asList("A", "B", "C");
        //Simple for each
        someList.stream().parallel().forEach(System.out::println);

        System.out.println("----------------");
        //forEachOrdered: order of processing is important
        someList.stream()
                .map(String::toLowerCase)
                .forEachOrdered(System.out::println);

    }
}
