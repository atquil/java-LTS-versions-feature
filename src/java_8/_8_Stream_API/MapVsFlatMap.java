package java_8._8_Stream_API;

import java.util.Arrays;
import java.util.List;

/**
 * @author atquil
 */
public class MapVsFlatMap {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8)
        );
        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(list -> list.stream())
                .toList();
        System.out.println(flattenedList); // [1, 2, 3, 4, 5, 6, 7, 8]

    }
}
