package java_8._6_DateTime;

import java.time.*;

/**
 * @author atquil
 */
public class DateTimeClass {
    public static void main(String[] args) {
        //These are the several methods that are useful, some of them are

        System.out.println("LocalDate in ISO calender:"+ LocalDate.now());
        System.out.println("ZonedDateTime in ISO calender:"+ ZonedDateTime.now());
        System.out.println("Offset from Greenwich/UTC without a time zone ID:"+ OffsetDateTime.now(ZoneId.systemDefault()));
        System.out.println("EPOCH, or Nanosecond or EPOCH time:"+ Instant.now());
        System.out.println("Period: Difference between dates:"+ Period.between(LocalDate.now(),LocalDate.of(1993,12,1)));

    }
}
