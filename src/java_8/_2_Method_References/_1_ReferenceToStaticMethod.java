package java_8._2_Method_References;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author atquil
 */
class Arithmetic{
    public static int add(int a, int b){
        return a+b;
    }

    public static Integer squareOfNumber(Integer integer) {
        return integer*integer;
    }
}
    @FunctionalInterface
interface SomeAbstractInterFace{
    void printSomething();
}
public class _1_ReferenceToStaticMethod {

    public static void someStaticMethod(){
        System.out.println("I am a static method");
    }

    public static void main(String[] args) {

        //Refer to static method
        SomeAbstractInterFace someAbstractInterFace = _1_ReferenceToStaticMethod::someStaticMethod;
        someAbstractInterFace.printSomething();

        //Using predefined functional interface
        Thread t = new Thread(_1_ReferenceToStaticMethod::someStaticMethod);
        t.start();

        //BiFunction <Arg1,Arg2,Result>
        BiFunction<Integer, Integer, Integer> add = Arithmetic::add;

        //Function<Arg,Result>
        Function<Integer, Integer> square = Arithmetic:: squareOfNumber;

        int result1 = add.apply(10, 20);
        int result2 = add.andThen(square).apply(10, 20);

        System.out.println("Addition of 10 and 20 is:"+result1);
        System.out.println("Addition and then square of 10 and 20 is :"+result2);
    }
}
