package java_21._6_RecordPattern;

import java.awt.*;

/**
 * @author atquil
 */

interface Shape {
    default void myShape(String myshape) {
        System.out.println("My Shape is ::" + myshape);
    }
}

record Circle(String type, long unit) implements Shape {
    @Override
    public void myShape(String myshape) {
        System.out.println("Circle shape: " + myshape);
    }
}

record Square(String type, long unit) implements Shape {
    @Override
    public void myShape(String myshape) {
        System.out.println("Square shape: " + myshape);
    }
}

public class RecordPattern {
    public static void main(String[] args) {
        Circle circle = new Circle("Circle", 50);
        circle.myShape("Circle");
        System.out.println(circle.type() + " with radius: " + circle.unit());

        Square square = new Square("Square",33);
        // Switch with Record
        switch ((Shape) circle) {
            case Circle c -> System.out.println("Switched to Circle");
            //Now can be shown like this as well
            case Square (String x, long size) -> System.out.println("Switched to Square");
            default -> System.out.println("Unknown shape");
        }
    }
}