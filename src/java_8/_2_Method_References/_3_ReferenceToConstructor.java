package java_8._2_Method_References;

/**
 * @author atquil
 */

class SomeClass{
    SomeClass() {
        System.out.println("Default constructor is being called");
    }
    SomeClass(String a){
        System.out.println("This will not be called");
    }
}
@FunctionalInterface
interface SomeInterface {
    SomeClass callConstructor();
}
public class _3_ReferenceToConstructor  {
    public static void main(String[] args) {

        SomeInterface someInterface = SomeClass::new;
        someInterface.callConstructor();
    }
}
