import java.util.ArrayList;

public class Ride {
    private String id, owner, pickup, destination, date, start, end;
    private String driver, driverContact, car, carNumber, carType, status;
    private int totalSeats, seats;
    private ArrayList<String> people;

    public Ride(String id, String owner, String pickup, String destination,
                String date, String start, String end, String driver,
                String driverContact, String car, String carNumber,
                String carType, int totalSeats) {

        this.id = id;
        this.owner = owner;
        this.pickup = pickup;
        this.destination = destination;
        this.date = date;
        this.start = start;
        this.end = end;
        this.driver = driver;
        this.driverContact = driverContact;
        this.car = car;
        this.carNumber = carNumber;
        this.carType = carType;
        this.totalSeats = totalSeats;
        this.seats = totalSeats - 1;
        this.status = "ACTIVE";
        this.people = new ArrayList<String>();
    }

    public Ride(String id, String owner, String pickup, String destination,
                String date, String start, String end, String driver,
                String driverContact, String car, String carNumber,
                String carType, int totalSeats, int seats,
                String status, ArrayList<String> people) {

        this.id = id;
        this.owner = owner;
        this.pickup = pickup;
        this.destination = destination;
        this.date = date;
        this.start = start;
        this.end = end;
        this.driver = driver;
        this.driverContact = driverContact;
        this.car = car;
        this.carNumber = carNumber;
        this.carType = carType;
        this.totalSeats = totalSeats;
        this.seats = seats;
        this.status = status;
        this.people = people;
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public String getPickup() { return pickup; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public String getStart() { return start; }
    public String getEnd() { return end; }
    public String getDriver() { return driver; }
    public String getDriverContact() { return driverContact; }
    public String getCar() { return car; }
    public String getCarNumber() { return carNumber; }
    public String getCarType() { return carType; }
    public int getTotalSeats() { return totalSeats; }
    public int getSeats() { return seats; }
    public String getStatus() { return status; }
    public ArrayList<String> getPeople() { return people; }

    public void addPerson(String id) {
        people.add(id);
        seats--;
    }

    public void removePerson(String id) {
        people.remove(id);
        seats++;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void show() {
        System.out.println(id + " | " + pickup + " -> " + destination +
                " | " + date + " | " + start + "-" + end +
                " | " + carType + " | Seats: " + seats +
                " | " + status);
    }
}
