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

    public Student findStudent(String id) {
        for (Student s : students)
            if (s.getId().equalsIgnoreCase(id))
                return s;

        return null;
    }

    public Ride findRide(String id) {
        for (Ride r : rides)
            if (r.getId().equalsIgnoreCase(id))
                return r;

        return null;
    }

    public void addStudent(Student s) throws RideException {
        if (s.getId().length() == 0 ||
            s.getName().length() == 0 ||
            s.getBranch().length() == 0 ||
            s.getContact().length() == 0)
            throw new RideException(
                    "Student details cannot be empty.");

        if (findStudent(s.getId()) != null)
            throw new RideException(
                    "Student already exists.");

        students.add(s);
    }

    public void addRide(Ride r) throws RideException {
        if (r.getId().length() == 0 ||
            r.getOwner().length() == 0 ||
            r.getPickup().length() == 0 ||
            r.getDestination().length() == 0)
            throw new RideException(
                    "Ride details cannot be empty.");

        if (findStudent(r.getOwner()) == null)
            throw new RideException(
                    "Student not found.");

        if (findRide(r.getId()) != null)
            throw new RideException(
                    "Ride already exists.");

        if (r.getTotalSeats() != 4 &&
            r.getTotalSeats() != 6)
            throw new RideException(
                    "Only 4 or 6 seater is allowed.");

        if (Matcher.time(r.getStart()) >=
            Matcher.time(r.getEnd()))
            throw new RideException(
                    "Invalid time range.");

        rides.add(r);
    }

    public ArrayList<Ride> matches(Ride wanted)
            throws RideException {

        ArrayList<Ride> result =
                new ArrayList<Ride>();

        for (Ride r : rides)
            if (Matcher.match(wanted, r))
                result.add(r);

        return result;
    }

    public void join(String rideId, String studentId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (findStudent(studentId) == null)
            throw new RideException(
                    "Student not found.");

        if (!r.getStatus().equals("ACTIVE"))
            throw new RideException(
                    "Ride is not active.");

        if (r.getOwner().equalsIgnoreCase(studentId))
            throw new RideException(
                    "Owner cannot join.");

        if (r.getSeats() <= 0)
            throw new RideException(
                    "Ride is full.");

        if (r.getPeople().contains(studentId))
            throw new RideException(
                    "Already joined.");

        r.addPerson(studentId);
    }

    public void leave(String rideId, String studentId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (!r.getPeople().contains(studentId))
            throw new RideException(
                    "Student not in ride.");

        if (!r.getStatus().equals("ACTIVE"))
            throw new RideException(
                    "Ride is not active.");

        r.removePerson(studentId);
    }

    public void cancel(String rideId, String studentId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (!r.getOwner().equalsIgnoreCase(studentId))
            throw new RideException(
                    "Only owner can cancel.");

        if (!r.getStatus().equals("ACTIVE"))
            throw new RideException(
                    "Ride is already inactive.");

        r.setStatus("CANCELLED");
    }

    public void complete(String rideId, String studentId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (!r.getOwner().equalsIgnoreCase(studentId))
            throw new RideException(
                    "Only owner can complete.");

        if (!r.getStatus().equals("ACTIVE"))
            throw new RideException(
                    "Ride is already inactive.");

        r.setStatus("COMPLETED");
    }

    public void remove(String rideId, String studentId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        if (!r.getOwner().equalsIgnoreCase(studentId))
            throw new RideException(
                    "Only owner can remove.");

        if (r.getPeople().size() > 0)
            throw new RideException(
                    "Ride has passengers.");

        rides.remove(r);
    }

    public void showPeople(String rideId)
            throws RideException {

        Ride r = findRide(rideId);

        if (r == null)
            throw new RideException(
                    "Ride not found.");

        Student owner =
                findStudent(r.getOwner());

        System.out.println("\nDriver: " + r.getDriver());
        System.out.println(
                "Driver Contact: " + r.getDriverContact());

        System.out.println("Car: " + r.getCar());
        System.out.println(
                "Car No: " + r.getCarNumber());

        System.out.println(
                "Car Type: " + r.getCarType());

        System.out.println(
                "Seats Left: " + r.getSeats());

        if (owner != null)
            System.out.println(
                    "Owner: " +
                    owner.getName() + " | " +
                    owner.getBranch() + " | " +
                    owner.getContact());

        System.out.println("Passengers:");

        if (r.getPeople().size() == 0) {
            System.out.println("None");
            return;
        }

        for (String id : r.getPeople()) {
            Student s = findStudent(id);

            if (s != null)
                s.show();
        }
    }
}
