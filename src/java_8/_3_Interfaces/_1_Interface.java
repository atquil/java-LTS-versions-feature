package java_8._3_Interfaces;

/**
 * @author atquil
 */

interface InterfaceContains{

    //Default Method
    default void someDefaultMethod(){
        System.out.println("Implemention of default method is must");
    }

    //Abstract Method
    void someAbstractMethod();

    //Static Method
    static void someStaticMethodWhichCanBeCalledDirectly(String str){
        System.out.println("Implementing static method::"+str);
    }

    //Object Method
    String toString();
}
public class _1_Interface implements InterfaceContains {

    public static void main(String[] args) {

        InterfaceContains.someStaticMethodWhichCanBeCalledDirectly("I am from main");

        _1_Interface somethingMethod = new _1_Interface();
        somethingMethod.someDefaultMethod();
        somethingMethod.someAbstractMethod();
    }

    //Overriding the default method
    @Override
    public void someDefaultMethod() {
        //Super to print the values contains in default method
        InterfaceContains.super.someDefaultMethod();
        System.out.println("Default method ends");
    }

    @Override
    public void someAbstractMethod() {
        System.out.println("I am Abstract Method");
    }
}
