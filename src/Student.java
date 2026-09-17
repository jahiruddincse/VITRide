public class Student {
    private String name, location, destination, date, time;

    public Student(String name, String location, String destination,
                   String date, String time) {
        this.name = name;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
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

    public void show() {
        System.out.println(name + " | " + location + " -> " +
                destination + " | " + date + " | " + time);
    }
}
