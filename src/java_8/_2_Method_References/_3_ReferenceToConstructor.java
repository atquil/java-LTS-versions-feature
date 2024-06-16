package java_8._2_Method_References;
class SomeClassForRefrence{
    SomeClassForRefrence() {
        System.out.println("Some Constructor Class is being called");
    }
}
@FunctionalInterface
interface SomeInterface{
    SomeClassForRefrence callConstructor();
}
public class _3_ReferenceToConstructor {
    public static void main(String[] args) {
        SomeInterface someInterface = SomeClassForRefrence::new;
        someInterface.callConstructor();
    }
}