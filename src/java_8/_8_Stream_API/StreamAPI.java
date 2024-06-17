package java_8._8_Stream_API;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author atquil
 */

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
