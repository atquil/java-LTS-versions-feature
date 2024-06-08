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