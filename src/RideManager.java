import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RideManager {

    private HashMap<String, Student> students = new HashMap<>();
    private ArrayList<Ride> rides = new ArrayList<>();

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void addStudent(Student student)
            throws InvalidRideException {

        if (students.containsKey(student.getId())) {
            throw new InvalidRideException(
                    "Student ID already exists."
            );
        }

        students.put(student.getId(), student);
    }

    public void addRide(Ride ride)
            throws InvalidRideException {

        if (!students.containsKey(ride.getOwnerId())) {
            throw new InvalidRideException(
                    "Ride owner does not exist."
            );
        }

        if (findRide(ride.getId()) != null) {
            throw new InvalidRideException(
                    "Ride ID already exists."
            );
        }

        if (!ride.getStartTime()
                .isBefore(ride.getEndTime())) {
            throw new InvalidRideException(
                    "End time must be after start time."
            );
        }

        if (ride.getSeats() <= 0) {
            throw new InvalidRideException(
                    "Seats must be greater than zero."
            );
        }

        rides.add(ride);
    }

    public Student findStudent(String id) {
        return students.get(id);
    }

    public Ride findRide(String id) {

        for (Ride ride : rides) {
            if (ride.getId().equalsIgnoreCase(id)) {
                return ride;
            }
        }

        return null;
    }

    public List<Ride> findMatches(
            String studentId,
            LocalDate date,
            String pickup,
            String destination,
            Ride myRide) {

        return rides.stream()
                .filter(r -> !r.getId().equals(myRide.getId()))
                .filter(r -> !r.getOwnerId()
                        .equalsIgnoreCase(studentId))
                .filter(r -> r.getSeats() > 0)
                .filter(r -> RideMatcher.matches(myRide, r))
                .sorted(
                        Comparator.comparing(
                                Ride::getStartTime
                        )
                )
                .collect(Collectors.toList());
    }

    public void joinRide(
            String rideId,
            String studentId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (!students.containsKey(studentId)) {
            throw new InvalidRideException(
                    "Student not found."
            );
        }

        if (!ride.getStatus().equals("ACTIVE")) {
            throw new InvalidRideException(
                    "Ride is not active."
            );
        }

        if (ride.getOwnerId()
                .equalsIgnoreCase(studentId)) {
            throw new InvalidRideException(
                    "Owner cannot join their own ride."
            );
        }

        if (ride.getSeats() <= 0) {
            throw new InvalidRideException(
                    "Ride is full."
            );
        }

        if (ride.getPassengers().contains(studentId)) {
            throw new InvalidRideException(
                    "Student already joined."
            );
        }

        ride.addPassenger(studentId);
    }

    public void leaveRide(
            String rideId,
            String studentId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (!ride.getPassengers().contains(studentId)) {
            throw new InvalidRideException(
                    "Student is not in this ride."
            );
        }

        ride.removePassenger(studentId);
    }

    public void cancelRide(
            String rideId,
            String studentId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (!ride.getOwnerId()
                .equalsIgnoreCase(studentId)) {
            throw new InvalidRideException(
                    "Only the owner can cancel the ride."
            );
        }

        ride.setStatus("CANCELLED");
    }

    public void completeRide(
            String rideId,
            String studentId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (!ride.getOwnerId()
                .equalsIgnoreCase(studentId)) {
            throw new InvalidRideException(
                    "Only the owner can complete the ride."
            );
        }

        ride.setStatus("COMPLETED");
    }

    public void removeRide(
            String rideId,
            String studentId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (!ride.getOwnerId()
                .equalsIgnoreCase(studentId)) {
            throw new InvalidRideException(
                    "Only the owner can remove the ride."
            );
        }

        rides.remove(ride);
    }

    public void showPassengers(String rideId)
            throws InvalidRideException {

        Ride ride = findRide(rideId);

        if (ride == null) {
            throw new InvalidRideException(
                    "Ride not found."
            );
        }

        if (ride.getPassengers().isEmpty()) {
            System.out.println("No passengers.");
            return;
        }

        Iterator<String> iterator =
                ride.getPassengers().iterator();

        while (iterator.hasNext()) {
            String id = iterator.next();
            Student student = findStudent(id);

            if (student != null) {
                System.out.println(student);
            }
        }
    }
}
