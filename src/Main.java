import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc =
            new Scanner(System.in);

    static Rides data =
            new Rides();

    public static void main(String[] args) {

        Data.load(data);

        while (true) {

            System.out.println();
            System.out.println("===== VITRIDE =====");
            System.out.println("1. Add Student");
            System.out.println("2. Create Ride");
            System.out.println("3. View Rides");
            System.out.println("4. Find Match");
            System.out.println("5. Join Ride");
            System.out.println("6. View Group");
            System.out.println("7. Leave Ride");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            try {

                int ch =
                        Integer.parseInt(
                                sc.nextLine());

                switch (ch) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        createRide();
                        break;

                    case 3:
                        viewRides();
                        break;

                    case 4:
                        findMatch();
                        break;

                    case 5:
                        joinRide();
                        break;

                    case 6:
                        viewGroup();
                        break;

                    case 7:
                        leaveRide();
                        break;

                    case 8:
                        save();
                        System.out.println(
                                "Goodbye.");
                        return;

                    default:
                        System.out.println(
                                "Invalid choice.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a number.");

            } catch (RideException e) {

                System.out.println(
                        "Error: " +
                        e.getMessage());
            }
        }
    }

    static void addStudent()
            throws RideException {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print(
                "Current Location: ");
        String location = sc.nextLine();

        System.out.print(
                "Going To: ");
        String destination =
                sc.nextLine();

        System.out.print(
                "Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print(
                "Start Time (HH:MM): ");
        String time = sc.nextLine();

        Matcher.minutes(time);

        data.addStudent(
                new Student(
                        name,
                        location,
                        destination,
                        date,
                        time));

        save();

        System.out.println(
                "Student added.");
    }

    static void createRide()
            throws RideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine();

        System.out.print("Your Name: ");
        String name = sc.nextLine();

        Student s =
                data.findStudent(name);

        if (s == null)
            throw new RideException(
                    "Student not found.");

        System.out.print("Total Fare: ");
        int fare =
                Integer.parseInt(
                        sc.nextLine());

        Ride r =
                new Ride(
                        id,
                        name,
                        s.getLocation(),
                        s.getDestination(),
                        s.getDate(),
                        s.getTime(),
                        fare);

        data.addRide(r);
        save();

        System.out.println(
                "Ride created.");
    }

    static void viewRides() {

        for (Ride r :
                data.getRides())
            r.show();
    }

    static void findMatch()
            throws RideException {

        System.out.print("Your Name: ");
        String name = sc.nextLine();

        Student s =
                data.findStudent(name);

        if (s == null)
            throw new RideException(
                    "Student not found.");

        ArrayList<Ride> result =
                data.findMatches(s);

        if (result.size() == 0) {

            System.out.println(
                    "No matching ride.");

            return;
        }

        for (Ride r : result) {

            System.out.println();
            System.out.println(
                    "MATCH FOUND");

            r.show();

            int a = Matcher.minutes(s.getTime());
            int b = Matcher.minutes(r.getTime());
            int diff = a - b;
            if (diff < 0)
                diff = -diff;

            System.out.println(
                    "Time difference: " +
                    diff + " minutes");

            System.out.println(
                    "Fare per person: " +
                    r.farePerPerson());
        }
    }

    static void joinRide()
            throws RideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine();

        System.out.print("Your Name: ");
        String name = sc.nextLine();

        data.join(id, name);
        save();

        System.out.println(
                "Joined ride.");

        data.showGroup(id);
    }

    static void viewGroup()
            throws RideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine();

        data.showGroup(id);
    }

    static void leaveRide()
            throws RideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine();

        System.out.print("Your Name: ");
        String name = sc.nextLine();

        data.leave(id, name);
        save();

        System.out.println(
                "Left ride.");
    }

    static void save() {

        try {

            Data.save(data);

        } catch (IOException e) {

            System.out.println(
                    "Could not save.");
        }
    }
}
