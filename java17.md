# Java 17
It was released on September 15, 2021.

**Spring 6 and Spring boot 3** will have first-class support for Java 17.

##  Sealed Classes  

- Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them
- Only Final and sealed class can use them 
```java
// class can be sealed
sealed class EmployeeAddress permits Employee {
    int pincode;

    public EmployeeAddress(int pincode) {
        this.pincode = pincode;
    }
}

// Interface can be sealed
sealed interface EmployeeSalaryCalculation permits Employee {
    default String getSalaryCalculation() {
        return "NoSalary"; // Placeholder implementation
    }
}

// Only a final class can use sealed class or interface. 
final class Employee extends EmployeeAddress implements EmployeeSalaryCalculation {
    public Employee(int pincode) {
        super(pincode);
    }
}

public class SealedClass {
    public static void main(String[] args) {
        // Create an Employee object
        Employee emp = new Employee(12345);

        // Access the pincode from EmployeeAddress
        System.out.println("Employee pincode: " + emp.pincode);

        // Call the salary calculation method
        System.out.println("Salary calculation: " + emp.getSalaryCalculation());
    }
}

```

Output::
```text
Employee pincode: 12345
Salary calculation: NoSalary
```

## Random Generator

The Random class provided methods like nextInt(), nextDouble(), and nextBoolean() to generate random values

With Java 17, a new interface called RandomGenerator was introduced to consolidate various random number generators. Also it introduced new classes like :

- SplittableGenerator – can be split into two objects (the original one and a new one) each of which obeys that same protocol.
- JumpableGenerator – can easily jump forward, by a moderate amount.
- LeapableGenerator – can easily not only jump but also leap forward, by a large amount.
- ArbitrarilyJumpableGenerator – can easily jump forward, by an arbitrary amount, to a distant point in the state cycle.
- StreamableGenerator – augments the RandomGenerator interface to provide methods that return streams of RandomGenerator objects.

Also, legacy random classes, such as Java.util.Random, SplittableRandom, ThreadLocalRandom and SecureRandom now extend the new RandomGenerator interface
```java
public class RandomGeneratorUse {
    public static void main(String[] args) {
        System.out.println("------Legacy---------");
        // 1. Using java.util.Random (legacy)
        Random legacyRandom = new Random();
        int legacyRandInt = legacyRandom.nextInt(100);
        System.out.println("Legacy Random Integer: " + legacyRandInt);

        // 2. Using ThreadLocalRandom (new)
        int newRandInt = ThreadLocalRandom.current().nextInt(100);
        System.out.println("New Random Integer: " + newRandInt);

        System.out.println("------RandomGenerator---------");
        // SplittableGenerator – can be split into two objects (the original one and a new one) each of which obeys that same protocol.
        SplittableRandom splittableRandom = new SplittableRandom();
        SplittableRandom splitRandom = splittableRandom.split();
        long splittableRandLong = splitRandom.nextLong();
        System.out.println("Splittable Random Long: " + splittableRandLong);

       
    }
}

```
Output:
```text
------Legacy---------
Legacy Random Integer: 79
New Random Integer: 74
------RandomGenerator---------
Splittable Random Long: -6828481640809286930
```
` NOTE:  that none of the new implementations are thread-safe while both Java.util.Random and Java.security.SecureRandom are.`

![img.png](src%2Fjava_17%2F_2_RandomGeneratorUse%2Fimg.png)

## Switch Pattern Matching (Preview) [In java 21]