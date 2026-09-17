import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ride {

    private String id;
    private String ownerId;
    private String pickup;
    private String destination;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int seats;
    private String status;
    private ArrayList<String> passengers;

    public Ride(String id, String ownerId, String pickup,
                String destination, LocalDate date,
                LocalTime startTime, LocalTime endTime,
                int seats) {

        this.id = id;
        this.ownerId = ownerId;
        this.pickup = pickup;
        this.destination = destination;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.status = "ACTIVE";
        this.passengers = new ArrayList<>();
    }

    public Ride(String id, String ownerId, String pickup,
                String destination, LocalDate date,
                LocalTime startTime, LocalTime endTime,
                int seats, String status,
                ArrayList<String> passengers) {

        this.id = id;
        this.ownerId = ownerId;
        this.pickup = pickup;
        this.destination = destination;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.status = status;
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPickup() {
        return pickup;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getSeats() {
        return seats;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void addPassenger(String studentId) {
        passengers.add(studentId);
        seats--;
    }

    public void removePassenger(String studentId) {
        if (passengers.remove(studentId)) {
            seats++;
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return id +
                " | " + pickup + " -> " + destination +
                " | " + date +
                " | " + startTime + "-" + endTime +
                " | Seats: " + seats +
                " | Owner: " + ownerId +
                " | " + status;
    }
}
