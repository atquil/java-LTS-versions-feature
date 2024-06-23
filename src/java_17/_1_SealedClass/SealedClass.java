package java_17._1_SealedClass;

/**
 * @author atquil
 */


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
