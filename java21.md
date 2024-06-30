# Java 21
Released on 19-Sep-2023

## SwitchWithPatternMatching

```java

class Employee{
    int id;
    Employee(Integer id){
        this.id =id;
    }
}
public class SwitchWithPatternMatching {
    public static void main(String[] args) {
        //Switch can happen on null as well
        System.out.println(checkUsingSwitch(null));
        System.out.println("----------------");
        // Switch on Object
        Employee employee = new Employee(1);
        System.out.println(checkUsingSwitch(employee));
        System.out.println("----------------");
        // Switch on String
        String someName = "Atquil";
        System.out.println(checkUsingSwitch(someName));
    }

    private static String checkUsingSwitch(Object object) {
        String returnString = "";
        switch (object){
            case null -> returnString ="Null object";
            case Employee e -> returnString = "Employee Id:: "+e.id;
            case String s -> returnString = "Object is of type string:"+s;
            default ->returnString ="Default";

        }
        return returnString;
    }
}


```
Object:

```text
Null object
----------------
Employee Id:: 1
----------------
Object is of type string:Atquil
```

## Virtual Thread

```text
Interview Question: 
Why we need virtual thread ?
One of the major issues with platform threads is that they run the code on the OS thread and capture the OS thread throughout its lifetime. There is a limit on the number of OS threads and this creates a scalability bottleneck.
```
The virtual threads are JVM-managed lightweight threads that will help in writing high-throughput concurrent applications

- With the introduction of virtual threads, it becomes possible to execute millions of virtual threads using only a few operating system threads. The most advantageous aspect is that there is no need to modify existing Java code. All that is required is instructing our application framework to utilize virtual threads in place of platform threads.
- Please note that virtual threads are not faster than platform threads. They should be used to scale the number of concurrent tasks that spend much of their time waiting.
- For example, server applications that handle many client requests and perform blocking I/O operations. For resource/processing-intensive tasks, continue using the traditional platform threads, as virtual threads will not provide any advantage.

