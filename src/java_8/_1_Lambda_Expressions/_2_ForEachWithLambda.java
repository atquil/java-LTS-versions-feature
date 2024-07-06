package java_8._1_Lambda_Expressions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atquil
 */
public class _2_ForEachWithLambda {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("Atul");
        list.add("Anand");
        list.add("Atquil");

        list.forEach((n)->System.out.println(n));
        //Can be sorted as
        list.forEach(System.out::println);
    }
}
