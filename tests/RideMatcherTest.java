import java.time.LocalDate;
import java.time.LocalTime;

public class RideMatcherTest {

    static void check(
            boolean condition,
            String message) {

        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void main(String[] args) {

        // Test 1: overlapping time windows - must match
        Ride a = new Ride(
                "R1", "S1",
                "VIT Bhopal", "Airport",
                LocalDate.of(2026, 9, 20),
                LocalTime.of(17, 0),
                LocalTime.of(17, 30),
                2
        );

        Ride b = new Ride(
                "R2", "S2",
                "VIT Bhopal", "Airport",
                LocalDate.of(2026, 9, 20),
                LocalTime.of(17, 20),
                LocalTime.of(17, 50),
                1
        );

        check(
                RideMatcher.matches(a, b),
                "Test 1 FAILED: Overlap not detected"
        );

        // Test 2: no time overlap - must not match
        Ride c = new Ride(
                "R3", "S3",
                "VIT Bhopal", "Airport",
                LocalDate.of(2026, 9, 20),
                LocalTime.of(18, 0),
                LocalTime.of(18, 30),
                1
        );

        check(
                !RideMatcher.matches(a, c),
                "Test 2 FAILED: Non-overlap wrongly matched"
        );

        // Test 3: different destination - must not match
        Ride d = new Ride(
                "R4", "S4",
                "VIT Bhopal", "Railway Station",
                LocalDate.of(2026, 9, 20),
                LocalTime.of(17, 20),
                LocalTime.of(17, 40),
                1
        );

        check(
                !RideMatcher.matches(a, d),
                "Test 3 FAILED: Different destination wrongly matched"
        );

        // Test 4: different date - must not match
        Ride e = new Ride(
                "R5", "S5",
                "VIT Bhopal", "Airport",
                LocalDate.of(2026, 9, 21),
                LocalTime.of(17, 20),
                LocalTime.of(17, 40),
                1
        );

        check(
                !RideMatcher.matches(a, e),
                "Test 4 FAILED: Different date wrongly matched"
        );

        System.out.println("All RideMatcher tests passed.");
    }
}
