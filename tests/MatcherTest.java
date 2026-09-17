public class MatcherTest {

    static void check(boolean x, String msg) {
        if (!x)
            throw new RuntimeException(msg);
    }

    public static void main(String[] args)
            throws Exception {

        Student s1 = new Student("Arjun", "VIT Bhopal", "Airport", "20-09-2026", "17:00");
        Student s2 = new Student("Priya", "VIT Bhopal", "Airport", "20-09-2026", "17:20");
        Student s3 = new Student("Rahul", "VIT Bhopal", "Airport", "20-09-2026", "18:00");
        Student s4 = new Student("Sneha", "VIT Bhopal", "Station", "20-09-2026", "17:15");
        Student s5 = new Student("Dev", "VIT Bhopal", "Airport", "21-09-2026", "17:10");

        Ride r1 = new Ride("R1", "Arjun", "VIT Bhopal", "Airport", "20-09-2026", "17:00", 600);

        // Test 1: S2 within 20 mins, same route and date -> match
        check(
                Matcher.match(s2, r1),
                "Within 30 mins match failed"
        );

        // Test 2: S3 is 60 mins away -> should not match
        check(
                !Matcher.match(s3, r1),
                "Over 30 mins should not match"
        );

        // Test 3: S4 has different destination -> should not match
        check(
                !Matcher.match(s4, r1),
                "Different destination should not match"
        );

        // Test 4: S5 has different date -> should not match
        check(
                !Matcher.match(s5, r1),
                "Different date should not match"
        );

        // Test 5: Owner cannot match own ride
        check(
                !Matcher.match(s1, r1),
                "Owner should not match own ride"
        );

        // Test 6: Fare split calculation
        r1.addMember("Priya");
        check(
                r1.farePerPerson() == 300.0,
                "Fare per person calculation failed"
        );

        System.out.println("All tests passed.");
    }
}
