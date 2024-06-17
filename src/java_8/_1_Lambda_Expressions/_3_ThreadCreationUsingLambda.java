package java_8._1_Lambda_Expressions;

/**
 * @author atquil
 */
public class _3_ThreadCreationUsingLambda {

    public static void main(String[] args) {

        try{
            //Thread Example without lambda
            Runnable r1=new Runnable(){
                public void run(){
                    System.out.println("Thread1 is running...");
                }
            };
            Thread t1=new Thread(r1);
            t1.start();

            //Thread Example with lambda
            Runnable r2=()->{
                System.out.println("Thread2 is running...");
            };
            Thread t2=new Thread(r2);
            t2.start();

        }catch (Exception e){
            System.out.println("Exception while running the thread");
        }
    }
}
