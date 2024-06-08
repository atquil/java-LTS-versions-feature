#Java Version 8
Oracle released Java 8 in March 18, 2014. Important new updates were

## Lambda expressions

Lambda expression helps us to write our code in **functional style**. 
It provides a clear and concise way to implement **SAM interface(Single Abstract Method)** by using an expression. 
It is very useful in a collection library in which it helps to iterate, filter and extract data.

Java lambda expression is treated as a function, so **compiler does not create .class file._**

```
Interview Question: What is Functional Interface ?

Lambda expression provides implementation of functional interface. **An interface which has only one abstract method is called functional interface**. 
Java provides an anotation **@FunctionalInterface**, which is used to declare an interface as functional interface.
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
@FunctionalInterface  //As it's an abstract method
interface Employee{
    public void name(String name);
}

@FunctionalInterface  //As it's an abstract method
interface EmployeeSalary{
    public int totalSalary(int ctc , int bonus);
}

public class LambdaExpressions {
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

        EmployeeSalary salary = (ctcComponent,bonusComponent) -> ctcComponent + bonusComponent;
        //This can be even sorted to   EmployeeSalary salary = Integer::sum;
        System.out.println("Total Salary of Employee:"+empName+" is "+salary.totalSalary(10,10));

    }
}


```
Output: 

```java
Employee Name is Aman
Total Salary of Employee:Aman is 20
```

### 
