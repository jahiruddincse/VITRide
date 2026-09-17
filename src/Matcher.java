public class Matcher {

    public static int time(String t) throws RideException {
        if (t == null || t.length() != 5 || t.charAt(2) != ':')
            throw new RideException("Use HH:MM format.");

        int h = (t.charAt(0) - '0') * 10 + (t.charAt(1) - '0');
        int m = (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');

        if (h > 23 || m > 59)
            throw new RideException("Invalid time.");

        return h * 60 + m;
    }

    public static boolean match(Ride a, Ride b) throws RideException {
        if (!a.getStatus().equals("ACTIVE") ||
            !b.getStatus().equals("ACTIVE"))
            return false;

        if (!a.getDate().equals(b.getDate()))
            return false;

        if (!a.getPickup().equalsIgnoreCase(b.getPickup()))
            return false;

        if (!a.getDestination().equalsIgnoreCase(b.getDestination()))
            return false;

        if (b.getSeats() <= 0)
            return false;

        if (a.getOwner().equalsIgnoreCase(b.getOwner()))
            return false;

        int a1 = time(a.getStart());
        int a2 = time(a.getEnd());
        int b1 = time(b.getStart());
        int b2 = time(b.getEnd());

        if (a1 >= a2 || b1 >= b2)
            return false;

        int start = a1 > b1 ? a1 : b1;
        int end = a2 < b2 ? a2 : b2;

        return start < end;
    }

    public static String commonTime(Ride a, Ride b)
            throws RideException {

        int a1 = time(a.getStart());
        int a2 = time(a.getEnd());
        int b1 = time(b.getStart());
        int b2 = time(b.getEnd());

        int start = a1 > b1 ? a1 : b1;
        int end = a2 < b2 ? a2 : b2;

        return show(start) + " - " + show(end);
    }

    static String show(int x) {
        int h = x / 60;
        int m = x % 60;

        String hs = h < 10 ? "0" + h : "" + h;
        String ms = m < 10 ? "0" + m : "" + m;

        return hs + ":" + ms;
    }
}
