import java.time.LocalTime;

public class RideMatcher {

    public static boolean matches(Ride a, Ride b) {

        if (!a.getStatus().equals("ACTIVE")
                || !b.getStatus().equals("ACTIVE")) {
            return false;
        }

        if (a.getDate() == null ||
                !a.getDate().equals(b.getDate())) {
            return false;
        }

        if (!a.getPickup().equalsIgnoreCase(b.getPickup())) {
            return false;
        }

        if (!a.getDestination()
                .equalsIgnoreCase(b.getDestination())) {
            return false;
        }

        LocalTime start = a.getStartTime().isAfter(b.getStartTime())
                ? a.getStartTime()
                : b.getStartTime();

        LocalTime end = a.getEndTime().isBefore(b.getEndTime())
                ? a.getEndTime()
                : b.getEndTime();

        return start.isBefore(end);
    }

    public static String commonTime(Ride a, Ride b) {

        LocalTime start = a.getStartTime().isAfter(b.getStartTime())
                ? a.getStartTime()
                : b.getStartTime();

        LocalTime end = a.getEndTime().isBefore(b.getEndTime())
                ? a.getEndTime()
                : b.getEndTime();

        return start + " - " + end;
    }
}
