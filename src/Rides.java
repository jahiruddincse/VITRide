import java.util.ArrayList;

public class Rides {

    private ArrayList<Student> students =
            new ArrayList<Student>();

    private ArrayList<Ride> rides =
            new ArrayList<Ride>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public Student findStudent(String name) {

        for (Student s : students) {
            if (s.getName()
                    .equalsIgnoreCase(name))
                return s;
        }

        return null;
    }

    public Ride findRide(String id) {

        for (Ride r : rides) {
            if (r.getId()
                    .equalsIgnoreCase(id))
                return r;
        }

        return null;
    }

    public void addStudent(Student s)
            throws RideException {

        if (findStudent(s.getName()) != null)
            throw new RideException(
                    "Student already exists.");

        students.add(s);
    }

    public void addRide(Ride r)
            throws RideException {

        if (findStudent(r.getOwner()) == null)
            throw new RideException(
                    "Student not found.");

        if (findRide(r.getId()) != null)
            throw new RideException(
                    "Ride already exists.");

        if (r.getFare() <= 0)
            throw new RideException(
                    "Invalid fare.");

        Matcher.minutes(r.getTime());

        rides.add(r);
    }

    public ArrayList<Ride> findMatches(
            Student s)
            throws RideException {

        ArrayList<Ride> result =
                new ArrayList<Ride>();

        for (Ride r : rides) {
            if (Matcher.match(s, r))
                result.add(r);
        }

        return result;
    }

    public void join(
            String rideId,
            String name)
            throws RideException {

        Ride r = findRide(rideId);
        Student s = findStudent(name);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (s == null)
            throw new RideException(
                    "Student not found.");

        if (r.getMembers().contains(name))
            throw new RideException(
                    "Already in ride.");

        if (!Matcher.match(s, r))
            throw new RideException(
                    "Student does not match.");

        r.addMember(name);
    }

    public void leave(
            String rideId,
            String name)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (name.equalsIgnoreCase(
                r.getOwner()))
            throw new RideException(
                    "Owner cannot leave.");

        if (!r.getMembers().contains(name))
            throw new RideException(
                    "Not in ride.");

        r.removeMember(name);
    }

    public void showGroup(
            String rideId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        r.show();

        System.out.println("Members:");

        for (String name :
                r.getMembers()) {

            Student s =
                    findStudent(name);

            if (s != null)
                s.show();
        }

        System.out.println(
                "Fare per person: " +
                r.farePerPerson());
    }
}
