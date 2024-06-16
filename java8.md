#Java Version 8
Oracle released Java 8 in March 18, 2014. Important new updates were

## Lambda expressions

Lambda expression helps us to write our code in **functional style**. 
It provides a clear and concise way to implement **SAM interface(Single Abstract Method)** by using an expression. 
It is beneficial in a collection library in which it helps to iterate, filter and extract data.

Java lambda expression is treated as a function, so **compiler does not create .class file._**

```
Interview Question: What is Functional Interface ?

Lambda expression provides implementation of functional interface. An interface which has only one abstract method is called functional interface. 
Java provides an anotation @FunctionalInterface, which is used to declare an interface as functional interface.
```
### Java Lambda Expression Syntax

```
(argument-list) -> {body}  

- Argument-list: It can be empty or non-empty as well.
- Arrow-token: It is used to link arguments-list and body of expression.
- Body: It contains expressions and statements for lambda expression.
```
### Simple Lambda Expression

```java
@FunctionalInterface  //Optional, as it's an abstract method
interface Employee{
    public void name(String name);
}

@FunctionalInterface  // Optional As it's an abstract method
interface EmployeeSalary{
    public int totalSalary(int ctc , int bonus);
}

public class _1_SimpleLambdaExpression {
    public static void main(String[] args) {
        String empName = "Aman";

        // Implementing Functional Interface without Lambda

        Employee functionalInterface = new Employee() {
            @Override
            public void name(String name) {
                System.out.println("Employee Name is "+name);
            }
        };
        functionalInterface.name(empName);

        Employee lambda = name -> System.out.println("Employee Name is "+name);

        //Without return keyword
        EmployeeSalary salary1 = (ctcComponent,bonusComponent) -> ctcComponent + bonusComponent;
        //This can be even sorted to   EmployeeSalary salary = Integer::sum;
        System.out.println("Total Salary of Employee:"+empName+" is "+salary1.totalSalary(10,10));

        // With return keyword
        EmployeeSalary salary2 = (ctcComponent,bonusComponent) -> {
            return ctcComponent + bonusComponent;
        };
        //This can be even sorted to   EmployeeSalary salary = Integer::sum;
        System.out.println("Total Salary of Employee:"+empName+" is "+salary2.totalSalary(10,10));

    }
}


```
Output: 

```java
Employee Name is Aman 
Total Salary of Employee:Aman is 20
Total Salary of Employee:Aman is 20
```

### forEach Loop 

Lambda Expression can be used to simplify the forEach loop

```java
public class _2_ForEachWithLambda {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("Atul");
        list.add("Anand");
        list.add("Atquil");

        list.forEach(
                (n)->System.out.println(n)
        );

        //Can be sorted as 
        list.forEach(
                System.out::println
        );
    }
}

```
Output:

```java
Atul
Anand
Atquil
Atul
Anand
Atquil
```

### Running thread using Lambda Expression 

As we know **Runnable** is an **Abstract interface** with only **run()** method, so we can use Lambda to simplify the creation of thread. 

```java
@FunctionalInterface
public interface Runnable {
    /**
     * Runs this operation.
     */
    void run();
}

```

Using Lambda for creating thread: 

```java
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

```

### Comparator using Lambda Expression


```
Interview Question: What is the difference between Comparator and Comparable ?
The Comparator interface in Java is used to sort a collection of objects based on specific criteria. While the Comparable interface allows comparison in a single way, the Comparator provides flexibility for sorting by multiple fields or criteria
Note: Comparator cannot compare premetive data type like int. 
```

```
Interview Quesiton: What is Method Reference ?
method reference is a concise way to refer to methods or constructors using the :: operator. It provides a shorthand notation for creating lambdas from existing methods. Here are the different types of method references
```

```java
class User{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

}
public class _4_ComparatorWithLambda {


    public static void main(String[] args) {
        System.out.println("************* Comparator With Lambda and more***** ");


        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Alice", "Smith", 30));
        userList.add(new User(2, "Bob", "Johnson", 25));
        userList.add(new User(3, "Charlie", "Brown", 28));

        System.out.println("************* Without Lambda ***** ");
        Comparator<User> byNameComparator = new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getFirstName().compareTo(u2.getFirstName());
            }
        };
        userList.sort(byNameComparator);
        userList.forEach(System.out::println);

        System.out.println("************* With Lambda Expression ***** ");
        Comparator<User> lastNameComparator1 = (u1, u2) -> u1.getLastName().compareTo(u2.getLastName());
        userList.sort(lastNameComparator1);
        userList.forEach(System.out::println); // method reference

        System.out.println("************* Lambda Expression using Method References ***** ");
        Comparator<User> lastNameComparator = Comparator.comparing(User::getLastName);
        // Print sorted list
        userList.sort(lastNameComparator);
        userList.forEach(System.out::println); // Method reference

        System.out.println("************* Reverse Comparator ***** ");
        Comparator<User> reverseComparator = lastNameComparator.reversed();
        userList.sort(reverseComparator);
        userList.forEach(System.out::println); // method reference

        System.out.println("************* Chaining Comparators :::: Lambda Comparator sorting using multiple fields ***** ");
        Comparator<User> fullNameComparator = Comparator.comparing(User::getFirstName).thenComparing(User::getLastName);
        userList.sort(fullNameComparator);
        userList.forEach(System.out::println);

        System.out.println("************* Parallel Sorting using comparator ***** ");
        List<User> sortedUser =
                userList.parallelStream().sorted(fullNameComparator).collect(Collectors.toList());
        sortedUser.forEach(System.out::println);

   }
}

```

### With Collection Data

```java
class StreamEmp{
   
    private int age;
    public StreamEmp( int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

public class _5_StreamUsingLambda {
    public static void main(String[] args) {
        List<StreamEmp> userList = new ArrayList<>();
        userList.add(new StreamEmp( 30));
        userList.add(new StreamEmp( 25));
        userList.add(new StreamEmp( 28));

        // using lambda to filter data
        Stream<StreamEmp> filterUser = userList.stream().filter(streamEmp -> streamEmp.getAge() > 25);
        filterUser.forEach(user -> System.out.println(user.getAge()));
    }
}

```

## Method Reference

Method reference is used to refer method of functional interface.It is a compact and easy form of lambda expression. Each time when you are using lambda expression to just referring a method, you can replace your lambda expression with method reference.

There are three ways of doing method references: 
1. Reference to a static method. 
2. Reference to an instance method. 
3. Reference to a constructor.

### Reference to a static method

```
Interview Question ? What is BiFunction ?
BiFunction is a functional interface, which also specify the return type of a function
@FunctionalInterface  
public interface BiFunction<Arg1,Arg2,Result>
public interface Function<Arg,Result>
 moreover have two method 
- apply() which returns the function result
- andThen() when needs to return composed function
```
```java
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

```

### Reference to an Instance Method

```java
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

```

### Reference to an Constructor

```java
class SomeClass{
    SomeClass() {
        System.out.println("Some Constructor Class is being called");
    }
}
@FunctionalInterface
interface SomeInterface{
    SomeClass callConstructor();
}
public class _3_ReferenceToConstructor {
    public static void main(String[] args) {
        SomeInterface someInterface = SomeClass::new;
        someInterface.callConstructor();
    }
}

```

```
Output:
Some Constructor Class
```

### Interface 

#### Interface Modification: 

An interface in Java is a blueprint of a class that defines the behavior of a class.
An interface helps in achieving **abstraction** i.e. hdiding the implementation details and showing only the functionaliity. 
A class that implements the interface, **must provide the body of all the abstract methods in the interface**. 

An interface can be used to achieve **multiple inheritence**, moreover interface can extend another interface, but a class can only extends one class. 

Interface represents **IS-A relationship** i.e, a class that implements an interface belongs to the type of that interface. 

A interface can have : **default, abstract , static , object ** methods. 

```java
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


```
Output::

```
Implementing static method::I am from main
It's the constructor
Implemention of default method is must
Default method ends
I am Abstract Method
--------------------------
Doing something
Age::2
```

#### Functional Interface
An Interface that contains **exactly one abstract method** is known as functional interface.
It can have **any number of default, static methods** but can contain only one abstract method. 
It can also declare methods of object class. 

Functional Interface is also known as **Single Abstract Method Interfaces or SAM Interfaces**

A functional interface will call the defalult constructor of a call. 

```java
class SomeClass{
    SomeClass() {
        System.out.println("Default constructor is being called");
    }
    SomeClass(String a){
        System.out.println("This will not be called");
    }
}
@FunctionalInterface
interface SomeInterface{
    SomeClass callConstructor();
}
public class _3_ReferenceToConstructor {
    public static void main(String[] args) {

        SomeInterface someInterface = SomeClass::new;
        someInterface.callConstructor();
    }
}

```

```
Output::
Default constructor is being called
```

```
Interview Question ?

What is the difference between Abstract Class and interface ?
Both are similar except that we can **create constructor** in abstract class whereas we can't in interface. 
```

2. A functional interface can extends another interface only **when it does not have any abstract method.**

### Optional Class

```
To deal with NullPointerException : java.util
```

These are some of the use case of Optional Class

```java
public class OptionalClass {
    public static void main(String[] args) {
        List<String> someString = Arrays.asList("A","B","C");

        Optional<List<String>> stringsValues = Optional.of(someString);

        System.out.println(stringsValues.isEmpty());
        System.out.println(stringsValues.isPresent());
        System.out.println(stringsValues.get());
        //It returns an empty Optional object. No value is present for this Optional.
        System.out.println(stringsValues.isEmpty());
        //It returns the hash code value of the present value, if any, or returns 0 (zero) if no value is present.
        System.out.println(stringsValues.hashCode());
        //It returns the contained value, if present, otherwise throw an exception to be created by the provided supplier.
        System.out.println(stringsValues.orElseThrow(IllegalArgumentException::new));
        //It returns the value if present, otherwise returns other.
        System.out.println(stringsValues.orElse(List.of("Some value")));
        //It returns a non-empty string representation of this Optional suitable for debugging
        System.out.println(stringsValues.toString());
        //Method
        someString.stream().filter(s -> s.contains("A")).forEach(System.out::println);

    }
}

```

Output::

```
false
true
[A, B, C]
false
94369
[A, B, C]
[A, B, C]
Optional[[A, B, C]]
A
```


### forEach

```
 **Collection classes** which extends **Iterable interface** use forEach loop **to iterate elements**.
```

```
What is the difference between forEach() and forEachOrdered() ?

forEach() : More efficient, helps in parallelStreams, here order of processing is not important
forEachOrdered(): When order of processing is crucial like when dealing with time-series data or sequence of element. 
```

```java
public class ForEachClass {

    public static void main(String[] args) {
        List<String> someList = List.of("A","B","C");
        //Simple for each
        someList.stream().parallel().forEach(System.out::println);

        System.out.println("----------------");
        //forEachOrdered: order of processing is important
        someList.stream()
                .map(String::toLowerCase)
                .forEachOrdered(System.out::println);

    }
}

```

Output::

```
B
C
A
----------------
a
b
c

```

### Date/Time API

Why need of new data and time API: 

Drawbacks of existing Date/Time API's :
1. **Thread safety**: The existing classes such as Date and Calendar does not provide thread safety. Hence it leads to hard-to-debug concurrency issues that are needed to be taken care by developers. The new Date and Time APIs of Java 8 provide thread safety and are immutable, hence avoiding the concurrency issue from developers. 
2. **Bad API designing**: The classic Date and Calendar APIs does not provide methods to perform basic day-to-day functionalities. The Date and Time classes introduced in Java 8 are ISO-centric and provides number of different methods for performing operations regarding date, time, duration and periods.
3. **Difficult time zone handling**: To handle the time-zone using classic Date and Calendar classes is difficult because the developers were supposed to write the logic for it. With the new APIs, the time-zone handling can be easily done with Local and ZonedDate/Time APIs.

New package introduced several classes like: 

```
public class DateTimeClass {
    public static void main(String[] args) {
        //These are the several methods that are useful, some of them are

        System.out.println("LocalDate in ISO calender:"+ LocalDate.now());
        System.out.println("ZonedDateTime in ISO calender:"+ ZonedDateTime.now());
        System.out.println("Offset from Greenwich/UTC without a time zone ID:"+ OffsetDateTime.now(ZoneId.systemDefault()));
        System.out.println("EPOCH, or Nanosecond or EPOCH time:"+ Instant.now());
        System.out.println("Period: Difference between dates:"+ Period.between(LocalDate.now(),LocalDate.of(1993,12,1)));

    }
}
```

Output::
```
LocalDate in ISO calender:2024-06-16
ZonedDateTime in ISO calender:2024-06-16T10:48:06.350829+05:30[Asia/Kolkata]
Offset from Greenwich/UTC without a time zone ID:2024-06-16T10:48:06.351181+05:30
EPOCH, or Nanosecond or EPOCH time:2024-06-16T05:18:06.351284Z
Period: Difference between dates:P-30Y-6M-15D
```

### 

