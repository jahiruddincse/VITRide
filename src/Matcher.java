public class Matcher {

    public static int minutes(String time)
            throws RideException {

        if (time == null || time.length() != 5 ||
                time.charAt(2) != ':')
            throw new RideException("Use HH:MM format.");

        int h = (time.charAt(0) - '0') * 10
                + (time.charAt(1) - '0');

        int m = (time.charAt(3) - '0') * 10
                + (time.charAt(4) - '0');

        if (h < 0 || h > 23 || m < 0 || m > 59)
            throw new RideException("Invalid time.");

        return h * 60 + m;
    }

    public static boolean match(
            Student s, Ride r)
            throws RideException {

        if (!s.getLocation()
                .equalsIgnoreCase(r.getLocation()))
            return false;

        if (!s.getDestination()
                .equalsIgnoreCase(r.getDestination()))
            return false;

        if (!s.getDate()
                .equals(r.getDate()))
            return false;

        if (s.getName()
                .equalsIgnoreCase(r.getOwner()))
            return false;

        int a = minutes(s.getTime());
        int b = minutes(r.getTime());

        int diff = a - b;

        if (diff < 0)
            diff = -diff;

        return diff <= 30;
    }
}
