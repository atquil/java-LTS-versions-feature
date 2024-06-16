package java_8._1_Lambda_Expressions;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author atquil
 */
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
