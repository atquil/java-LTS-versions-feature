package java_8._3_Interfaces;

import javax.swing.plaf.PanelUI;

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

abstract class SomeAbstractClass{
    //Instead of default method we have Constructor
    public SomeAbstractClass(){
        System.out.println("It's the constructor");
    }

    //Abstract method with no method body
    abstract String someAbstractMethodFromClass();

    static void someStaticMethod(int a){
        System.out.println("Age::"+a);
    }

}
public class _1_Interface extends SomeAbstractClass implements InterfaceContains {

    public static void main(String[] args) {

        InterfaceContains.someStaticMethodWhichCanBeCalledDirectly("I am from main");

        _1_Interface somethingMethod = new _1_Interface();
        somethingMethod.someDefaultMethod();
        somethingMethod.someAbstractMethod();

        System.out.println("--------------------------");
        //AbstractClass
        somethingMethod.someAbstractMethodFromClass();
        SomeAbstractClass.someStaticMethod(2);

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

    //Implementing Abstract method from class
    @Override
    String someAbstractMethodFromClass() {
        System.out.println("Doing something");
        return "Something";
    }
}
