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

```
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

```
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

        //_3_PredicateAndBiPredicate <Arg1,Arg2,Result>
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

## Interface 

### Interface Modification: 

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

### Functional Interface
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

### Predicate and BiPredicate

**Predicate** is a functional interface that represents a boolean-valued function that **takes one argument**. 
Similarly, a **BiPredicate** is an interface that extends Predicate to handle **two input arguments**

Imp: If we have return type only boolean, instead of defining some method name, we can use them 
Reason: BiPredicate provide some useful chaining. 

```java
public class _3_PredicateAndBiPredicate {
    public static void main(String[] args) {


        //IsSum Even.
        BiPredicate<Integer, Integer> isSumEven = (a, b) -> (a + b) % 2 == 0;
        boolean result1 = isSumEven.test(10, 20);
        System.out.println("isSumEven"+result1);

        //Is Sum greater then 20
        BiPredicate<Integer, Integer> isSumGreater = (a, b) -> (a + b) > 10;
        boolean result2 = isSumEven.test(10, 20);
        System.out.println("isSumGreaterThen10"+result2);


        //Chaining with and
        BiPredicate<Integer, Integer> combined = isSumEven.and(isSumGreater);
        boolean result3 = combined.test(10, 20);
        System.out.println("isSumEvenAndGreaterThen10"+result3);

        //Chaining with or
        BiPredicate<Integer, Integer> only = isSumEven.or(isSumGreater);
        boolean result4 = only .test(1, 20);
        System.out.println("isSumEvenOrGreaterThen10"+result4);

        //With Streams
        List<String> names = Arrays.asList("Atquil", "Atul", "Anand");
        BiPredicate<String, Integer> isNameLengthGreaterThan = (name, length) -> name.length() > length;
        List<String> longNames = names.stream()
                .filter(name -> isNameLengthGreaterThan.test(name, 5))
                .collect(Collectors.toList());
        System.out.println("Names with length greater than 5 characters: " + longNames);
    }
}


```
Output::
```
isSumEventrue
isSumGreaterThen10true
isSumEvenAndGreaterThen10true
isSumEvenOrGreaterThen10true
Names with length greater than 5 characters: [Atquil]
```

## Optional Class

```
To deal with NullPointerException : java.util
```

These are some of the use case of Optional Class

```java
public class OptionalClass {
    public static void main(String[] args) {
        List<String> someString = Arrays.asList("A","B","C");

        Optional<List<String>> stringsValues = Optional.of(someString);

        System.out.println(stringsValues.isPresent());
        //It returns an empty Optional object. No value is present for this Optional.
        System.out.println(stringsValues.get());

        //It returns the hash code value of the present value, if any, or returns 0 (zero) if no value is present.
        System.out.println(stringsValues.hashCode());
        //It returns the contained value, if present, otherwise throw an exception to be created by the provided supplier.
        System.out.println(stringsValues.orElseThrow(IllegalArgumentException::new));

        //It returns a non-empty string representation of this Optional suitable for debugging
        System.out.println(stringsValues.toString());

        //Method
        someString.stream().filter(s -> s.contains("A")).forEach(System.out::println);


    }
}


```

Output::

```
true
[A, B, C]
94369
[A, B, C]
Optional[[A, B, C]]
A
```


## forEach

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

## Date/Time API

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

## String joiner

What ?
- final class StringJoiner in java.util package.
- StringJoiner(CharSequence delimiter): It is used to construct a sequence of characters separated by a delimiter e.g. (,), (-)
- StringJoiner(CharSequence delimiter,CharSequence prefix,CharSequence suffix) : We can also add **prefix** and **suffix**

```java
public class StringJoiners {
    public static void main(String[] args) {

        StringJoiner someJoiners = new StringJoiner(",","Names:",": End");

        // We can set default empty value.
        someJoiners.setEmptyValue("Default Value");
        System.out.println(someJoiners);

        // Adding values to StringJoiner
        someJoiners.add("Atul");
        someJoiners.add("Anand");
        someJoiners.add("Atquil");

        System.out.println(someJoiners);
        System.out.println("CharLength: "+someJoiners.length());
        System.out.println("To String: "+someJoiners.toString());
    }
}

```

## Stream API

- java.util.stream
- This package consists of classes, interfaces and enum to allows **functional-style operations on the elements**

Features of stream::
1. Functional in nature
2. It **does not modify it's source** but just process the data. 
3. It's Lazy and evaluates code only when required.
4. One element is visited only once in it's lifetime. 

There are many use-case for Stream, listing some of them here :: 

```java
class Employee{
    int id;
    String name;
    float salary;

    public Employee(int id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getCompanyName(){
        return "Atquil";
    }
}
public class StreamAPI {
    public static void main(String[] args) {
        //Let's create a List of Employee
        List<Employee> employees = new ArrayList<>();
        //Employee:
        employees.add( new Employee(1,"Atquil",100));
        employees.add(new Employee(2,"Atul",500));
        employees.add(new Employee(3,"Anand",300));
        employees.add(new Employee(4,"Atul",300));

        //Filtering data based on parameter
        employees.stream().filter(employee -> employee.salary > 400).forEach(employee -> System.out.println(employee.name + " is getting "+employee.salary+" salary"));

        System.out.println("-------------------");

        //Map: What data you want to extract
        employees.stream()
                .filter(employee -> employee.salary >200)
                .map(employee -> employee.name)
                .forEach(System.out::println);
        System.out.println("-------------------");

        double totalSalary = employees.stream()
                .filter(employee -> employee.salary >200)
                .mapToDouble(employee -> employee.salary)
                .sum();
        System.out.println("Total Salary:"+totalSalary);
        System.out.println("-------------------");


        // Limit: when you want to limit the processing numbers
        employees.stream()
                .filter(employee -> employee.salary >200)
                .map(employee -> employee.name)
                .limit(1) // Only one time it will run
                .forEach(System.out::println);
        System.out.println("-------------------");

        //Count
        long totalEmployee = employees.stream()
                .filter(employee -> employee.salary > 200)
                .count();
        System.out.println("Total Emp salary greater than 200"+totalEmployee);


        // Collectors ::
        double totalUsingColletor = employees.stream()
                .filter(employee -> employee.salary >200)
                .collect(Collectors.summingDouble(emp-> emp.salary));
        System.out.println("Total Salary:"+totalUsingColletor);
        System.out.println("-------------------");

        int totalUsingColletorToInt = employees.stream()
                .filter(employee -> employee.salary >200)
                .collect(Collectors.summingInt(emp-> (int) emp.salary));
        System.out.println("Total Salary:"+totalUsingColletorToInt);
        System.out.println("-------------------");

        double averagingDouble = employees.stream()
                .filter(employee -> employee.salary >200)
                .collect(Collectors.averagingDouble(emp-> emp.salary));
        System.out.println("Average Salary:"+averagingDouble);
        System.out.println("-------------------");

        // Collect: When you want to store data
        List<String> empNames =  employees.stream()
                .filter(employee -> employee.salary >200)
                .map(employee -> employee.name)
                .collect(Collectors.toList());
        empNames.forEach(System.out::println);
        System.out.println("-------------------");

        Set<String> setOfNames =  employees.stream()
                .filter(employee -> employee.salary >200)
                .map(employee -> employee.name)
                .collect(Collectors.toSet()); //Remove duplicate names if any
        setOfNames.forEach(System.out::println);
        System.out.println("-------------------");

        Map<Integer,String> mapOfNames =  employees.stream()
                .collect(Collectors.toMap(emp->emp.id,emp->emp.name)); //Remove duplicate names if any

        // entrySet() to iterate over Map
        mapOfNames.entrySet().forEach(System.out::println);
        System.out.println("-------------------");

        //Counting
        long totalEmp = employees.stream()
                .filter(employee -> employee.salary >200)
                .collect(Collectors.counting());
        System.out.println("Total Emp:"+totalEmp);
        System.out.println("-------------------");

        // Method Reference in Stream
        List<String> companyNames = employees.stream()
                .filter(employee -> employee.salary >200)
                .map(Employee::getCompanyName)
                .collect(Collectors.toList());
        companyNames.forEach(System.out::println);

        System.out.println("---There are many more please check based on utilisation-");

    }
}

```

Output::

```

Atul is getting 500.0 salary
-------------------
Atul
Anand
Atul
-------------------
Total Salary:1100.0
-------------------
Atul
-------------------
Total Emp salary greater than 2003
Total Salary:1100.0
-------------------
Total Salary:1100
-------------------
Average Salary:366.6666666666667
-------------------
Atul
Anand
Atul
-------------------
Atul
Anand
-------------------
1=Atquil
2=Atul
3=Anand
4=Atul
-------------------
Total Emp:3
-------------------
Atquil
Atquil
Atquil
---There are many more please check based on utilisation-
```
## Future and CompletableFuture

- **java.util.concurrent**
**_Future_**: It represents a future result of an asynchronous computation; a result that will eventually appear in the Future after the processing is complete. However, it does not allow you to manually complete it with a value or an exception, and it doesn’t provide methods to combine these futures or handle possible errors.
**_CompletableFuture_**: It is an extension of Future that allows you to manually complete it with a value or an exception, and it provides a lot of methods for composing, combining, and handling errors for asynchronous computations. This makes CompletableFuture more flexible and powerful than Future.


In the Future example, you have to wait for the computation to complete with get(), which blocks the current thread. In the CompletableFuture example, you can attach callbacks such as thenAccept() that will be executed once the computation is complete, without blocking the current thread. You can also use get() if you need to wait for the result.

```java
public class _1_FutureVsCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);


        Future<Integer> future = executorService.submit(() -> {
            // Simulate long running task
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Future Thread Pool Executor::"+Thread.currentThread().getName());
            return 123;
        });

        // --- We can do some calculation this the timeOut
        System.out.println("Executed First");

        //Blocking, as if we need to use the future return value it will be blocked here
        Integer result = future.get(); // This line blocks, waiting for the result
        executorService.shutdown();

        System.out.println("CompletableFuture");
        // CompletableFuture
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
        // Simulate long running task
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("CompletableFuture Thread Pool Executor::"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 123;
        });

        // ... do something else ...
        System.out.println("Non-blocking");
        // Non-blocking and can handle the result with a callback
        completableFuture.thenAccept(result2 -> System.out.println(result));

        System.out.println("Still non blocking");
        // Or block and get the result directly, if needed
        Integer result2 = completableFuture.get();
    }
}

```

Output::
```
Executed First
Future Thread Pool Executor::pool-1-thread-1
CompletableFuture
Non-blocking
Still non blocking
CompletableFuture Thread Pool Executor::ForkJoinPool.commonPool-worker-1
123


```
CompletableFuture offers an extensive API consisting of more than 50 methods.
Many of these methods are available in two variants: **Sync and async.**

### Async Methods 

CompletableFuture provides a powerful and flexible way to write **asynchronous, non-blocking code** that runs on different **thread**

1. It uses **static method supplyAsync**, a lambda function to reutrn CompletebleFuture Object
2. Composing CompletableFuture: **thenApply, thenCombine, thenCompose** to perform operation on results
3. Exception handling using: ** handle, exceptionally**
4. Runs on a separate **threadPool**

```java
public class _2_Asynchronous {
    public static void main(String[] args)  {
        System.out.println("Main thread::"+Thread.currentThread().getName());
        // SupplyAsync
        try {

        CompletableFuture<String> someCounting = CompletableFuture
                .supplyAsync(()-> {
                    System.out.println("Thread Pool::"+Thread.currentThread().getName());
                    // Simulate long running task
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Done";
                });
        CompletableFuture<Integer> charLength = someCounting.thenApplyAsync(value -> {
                System.out.println("Thread Pool::"+Thread.currentThread().getName());
                return value.length();
            });
        System.out.println(charLength.get());
        } catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }

        // As we can see above all thread pool is overloaded with only ForkJoinPool.commonPool-worker-1. Let's add custom Executor

        //Custom Executor
        Executor someExecutor = Executors.newFixedThreadPool(2);
        try {

            CompletableFuture<String> someCounting = CompletableFuture
                    .supplyAsync(()-> {
                        System.out.println("Thread Pool Executor::"+Thread.currentThread().getName());
                        // Simulate long running task
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            throw new IllegalStateException(e);
                        }
                        return "Done";
                    },someExecutor);
            CompletableFuture<Integer> charLength = someCounting.thenApplyAsync(value -> {
                System.out.println("Thread Pool Executor::"+Thread.currentThread().getName());
                return value.length();
            },someExecutor);
            System.out.println(charLength.get());
        } catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }



        try {
            CompletableFuture<String> getNames = CompletableFuture
                    .supplyAsync(()->"Atquil");
            CompletableFuture<Integer> getViewers = CompletableFuture
                    .supplyAsync(()->10);

            //Now first, let's combine the result of both
            CompletableFuture<String> getNameAndViewers = getViewers.thenCombine(getNames,(first,second)->first +" "+ second);

            System.out.println(getNameAndViewers.get());

        }catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }


        //Handle Exception

        CompletableFuture<Integer> getNames = CompletableFuture
                .supplyAsync(()->101/0)
                .exceptionally(throwable -> 101010); //If exception return this value

        try {
            System.out.println("Return value::"+getNames.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Output:::
```
Main thread::main
Thread Pool::ForkJoinPool.commonPool-worker-1
Thread Pool::ForkJoinPool.commonPool-worker-1
4
Thread Pool Executor::pool-1-thread-1
Thread Pool Executor::pool-1-thread-2
4
10 Atquil
Return value::101010
```

### Synchronous Methods

utilizing **thenApply()**, we pass a function as a parameter that takes the previous value of the CompletableFuture as input, performs an operation, and returns a new value


```java
public class _3_Synchronous {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main thread::"+Thread.currentThread().getName());

        CompletableFuture<String> name = CompletableFuture.supplyAsync(() ->{
            System.out.println("Async so different thread"+Thread.currentThread().getName());
            return "Atquil";
        });
        CompletableFuture<Integer> nameLength = name.thenApply(value -> {
            System.out.println("Synchronous, so parent thread::"+Thread.currentThread().getName());
            return value.length();
        });
        System.out.println("Name:: "+name.get());
        System.out.println("CharLength::"+nameLength.get());


    }
}


```

Output:::

```
Main thread::main
Async so different threadForkJoinPool.commonPool-worker-1
Synchronous, so parent thread::main
Name:: Atquil
CharLength::6

```


## Collection 

## Base64 Encoding/Encryption and Decoding/Decryption

## Parallel Sorting : Behave same, but use multithreading.
Java Collection classes are **fail-fast**, which means if the Collection will be changed while some thread is traversing over it using iterator, the iterator.next() will throw ConcurrentModificationException.

## Using Nashorn, now we can execute Javascript file in Java  [Just know that there is a way]


