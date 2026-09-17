public class MatcherTest {

    static void check(boolean x, String msg) {
        if (!x)
            throw new RuntimeException(msg);
    }

    public static void main(String[] args)
            throws Exception {

        Ride a =
                new Ride(
                        "R1",
                        "S1",
                        "VIT Bhopal",
                        "Airport",
                        "20-09-2026",
                        "17:00",
                        "17:30",
                        "Aman",
                        "9876543210",
                        "Swift",
                        "MP01AA1111",
                        "4 Seater",
                        4
                );

        Ride b =
                new Ride(
                        "R2",
                        "S2",
                        "VIT Bhopal",
                        "Airport",
                        "20-09-2026",
                        "17:20",
                        "17:50",
                        "Rahul",
                        "9876543211",
                        "Baleno",
                        "MP01BB2222",
                        "6 Seater",
                        6
                );

        check(
                Matcher.match(a, b),
                "Overlap failed"
        );

        check(
                Matcher.commonTime(a, b)
                        .equals(
                                "17:20 - 17:30"),
                "Common time failed"
        );

        Ride c =
                new Ride(
                        "R3",
                        "S3",
                        "VIT Bhopal",
                        "Airport",
                        "20-09-2026",
                        "18:00",
                        "18:30",
                        "A",
                        "1",
                        "WagonR",
                        "MP01CC3333",
                        "4 Seater",
                        4
                );

        check(
                !Matcher.match(a, c),
                "Time failed"
        );

        Ride d =
                new Ride(
                        "R4",
                        "S4",
                        "VIT Bhopal",
                        "Station",
                        "20-09-2026",
                        "17:20",
                        "17:40",
                        "D",
                        "2",
                        "Alto",
                        "MP01DD4444",
                        "4 Seater",
                        4
                );

        check(
                !Matcher.match(a, d),
                "Destination failed"
        );

        Ride e =
                new Ride(
                        "R5",
                        "S5",
                        "VIT Bhopal",
                        "Airport",
                        "21-09-2026",
                        "17:20",
                        "17:40",
                        "E",
                        "3",
                        "i10",
                        "MP01EE5555",
                        "4 Seater",
                        4
                );

        check(
                !Matcher.match(a, e),
                "Date failed"
        );

        System.out.println(
                "All tests passed.");
    }
}
