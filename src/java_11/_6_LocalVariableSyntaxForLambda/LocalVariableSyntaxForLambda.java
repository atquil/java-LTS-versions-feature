package java_11._6_LocalVariableSyntaxForLambda;

import lombok.NonNull;

import java.beans.Expression;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author atquil
 */
public class LocalVariableSyntaxForLambda {
    public static void main(String[] args) {

        List<String> someNames = List.of("ATQUIL", "ATUL", "ANAND");


        // Using an inline lambda expression with filter
        Optional<String> result = someNames.stream()
                .filter(name -> name.equals("ATUL"))
                .findFirst();

        System.out.println("--------Explicit Lambda Expression------");
        result.ifPresentOrElse(
                (String foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
        System.out.println("--------Implicit Lambda Expression with var ------");
        result.ifPresentOrElse(
                (var foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
        System.out.println("--------Implicit Lambda Expression without type ------");
        result.ifPresentOrElse(
                (foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );

        // But why write "var" when you can omit the types completely,
        System.out.println("--------Implicit Lambda Expression without type ------");
        result.ifPresentOrElse(
                (@NonNull var foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
    }
}
