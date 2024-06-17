package java_8._2_Method_References;

/**
 * @author atquil
 */

public class _2_ReferenceToInstanceMethod {
    public static void main(String[] args) {
        Thread t = new Thread(new _2_ReferenceToInstanceMethod()::someMethod);
        t.start();
    }

    //Instance Method
    void someMethod(){
        System.out.println("I am a instance method");
    }
}
