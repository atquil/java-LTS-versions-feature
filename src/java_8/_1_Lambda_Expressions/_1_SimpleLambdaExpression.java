package java_8._1_Lambda_Expressions;

/**
 * @author atquil
 */

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
        //This can be even sorted to EmployeeSalary salary = Integer::sum;
        System.out.println("Total Salary of Employee:"+empName+" is "+salary1.totalSalary(10,10));

        // With return keyword
        EmployeeSalary salary2 = (ctcComponent,bonusComponent) -> {
            return ctcComponent + bonusComponent;
        };
        //This can be even sorted to   EmployeeSalary salary = Integer::sum;
        System.out.println("Total Salary of Employee:"+empName+" is "+salary2.totalSalary(10,10));

    }
}
