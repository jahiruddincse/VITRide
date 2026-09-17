import java.util.ArrayList;

public class Ride {
    private String id, owner, location, destination, date, time;
    private int fare;
    private ArrayList<String> members;

    public Ride(String id, String owner, String location,
                String destination, String date,
                String time, int fare) {

        this.id = id;
        this.owner = owner;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.fare = fare;

        members = new ArrayList<String>();
        members.add(owner);
    }

    public Ride(String id, String owner, String location,
                String destination, String date,
                String time, int fare,
                ArrayList<String> members) {

        this.id = id;
        this.owner = owner;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.fare = fare;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getLocation() {
        return location;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getFare() {
        return fare;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void addMember(String name) {
        members.add(name);
    }

    public void removeMember(String name) {
        members.remove(name);
    }

    public double farePerPerson() {
        return (double) fare / members.size();
    }

    public void show() {
        System.out.println(
                id + " | " +
                location + " -> " +
                destination + " | " +
                date + " | " +
                time + " | Fare: " +
                fare + " | Members: " +
                members.size()
        );
    }
}
