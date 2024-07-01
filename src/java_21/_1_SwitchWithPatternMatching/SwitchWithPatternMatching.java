package java_21._1_SwitchWithPatternMatching;

/**
 * @author atquil
 */

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

