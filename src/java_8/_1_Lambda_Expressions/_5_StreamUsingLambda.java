package java_8._1_Lambda_Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author atquil
 */

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
