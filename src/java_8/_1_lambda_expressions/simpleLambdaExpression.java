package java_8._1_lambda_expressions;

/**
 * @author atquil
 */

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
