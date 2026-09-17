import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Rides data = new Rides();

    public static void main(String[] args) {

        Data.load(data);

        while (true) {

            System.out.println("\n===== VITRIDE =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Create Ride");
            System.out.println("5. View Rides");
            System.out.println("6. Find Ride Match");
            System.out.println("7. Join Ride");
            System.out.println("8. Leave Ride");
            System.out.println("9. View Ride Members");
            System.out.println("10. Cancel Ride");
            System.out.println("11. Complete Ride");
            System.out.println("12. Remove Ride");
            System.out.println("13. Save");
            System.out.println("14. Exit");
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
                        viewStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        createRide();
                        break;

                    case 5:
                        viewRides();
                        break;

                    case 6:
                        findMatch();
                        break;

                    case 7:
                        join();
                        break;

                    case 8:
                        leave();
                        break;

                    case 9:
                        people();
                        break;

                    case 10:
                        cancel();
                        break;

                    case 11:
                        complete();
                        break;

                    case 12:
                        remove();
                        break;

                    case 13:
                        save();
                        break;

                    case 14:
                        save();
                        System.out.println("Goodbye.");
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

        System.out.print("Student ID: ");
        String id = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Branch: ");
        String branch = sc.nextLine();

        System.out.print("Contact: ");
        String contact = sc.nextLine();

        data.addStudent(
                new Student(
                        id,
                        name,
                        branch,
                        contact
                )
        );

        save();

        System.out.println(
                "Student added.");
    }

    static void viewStudents() {

        for (Student s :
                data.getStudents()) {

            s.show();
        }
    }

    static void searchStudent() {

        System.out.print(
                "Student ID: ");

        Student s =
                data.findStudent(
                        sc.nextLine());

        if (s == null)
            System.out.println(
                    "Student not found.");
        else
            s.show();
    }

    static void createRide()
            throws RideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine();

        System.out.print("Owner ID: ");
        String owner = sc.nextLine();

        System.out.print("Pickup: ");
        String pickup = sc.nextLine();

        System.out.print("Destination: ");
        String destination =
                sc.nextLine();

        System.out.print(
                "Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print(
                "Start (HH:MM): ");
        String start = sc.nextLine();

        System.out.print(
                "End (HH:MM): ");
        String end = sc.nextLine();

        System.out.print(
                "Driver Name: ");
        String driver = sc.nextLine();

        System.out.print(
                "Driver Contact: ");
        String driverContact =
                sc.nextLine();

        System.out.print("Car: ");
        String car = sc.nextLine();

        System.out.print(
                "Car No: ");
        String carNumber =
                sc.nextLine();

        System.out.print(
                "Car Type (4/6): ");

        int totalSeats =
                Integer.parseInt(
                        sc.nextLine());

        String carType;

        if (totalSeats == 4)
            carType = "4 Seater";
        else if (totalSeats == 6)
            carType = "6 Seater";
        else
            throw new RideException(
                    "Only 4 or 6 seater is allowed.");

        Ride r =
                new Ride(
                        id,
                        owner,
                        pickup,
                        destination,
                        date,
                        start,
                        end,
                        driver,
                        driverContact,
                        car,
                        carNumber,
                        carType,
                        totalSeats
                );

        data.addRide(r);
        save();

        System.out.println(
                "Ride created.");

        System.out.println(
                "Available student seats: " +
                r.getSeats());
    }

    static void viewRides() {

        for (Ride r :
                data.getRides()) {

            r.show();
        }
    }

    static void findMatch()
            throws RideException {

        System.out.print(
                "Your ID: ");

        String id = sc.nextLine();

        if (data.findStudent(id) == null)
            throw new RideException(
                    "Student not found.");

        System.out.print("Pickup: ");
        String pickup = sc.nextLine();

        System.out.print(
                "Destination: ");

        String destination =
                sc.nextLine();

        System.out.print(
                "Date (DD-MM-YYYY): ");

        String date = sc.nextLine();

        System.out.print(
                "Start (HH:MM): ");

        String start = sc.nextLine();

        System.out.print(
                "End (HH:MM): ");

        String end = sc.nextLine();

        Ride wanted =
                new Ride(
                        "SEARCH",
                        id,
                        pickup,
                        destination,
                        date,
                        start,
                        end,
                        "",
                        "",
                        "",
                        "",
                        "4 Seater",
                        4
                );

        ArrayList<Ride> result =
                data.matches(wanted);

        if (result.size() == 0) {

            System.out.println(
                    "No compatible ride found.");

            return;
        }

        for (Ride r : result) {

            System.out.println(
                    "\nMatch found");

            r.show();

            System.out.println(
                    "Driver: " +
                    r.getDriver());

            System.out.println(
                    "Driver Contact: " +
                    r.getDriverContact());

            System.out.println(
                    "Car: " +
                    r.getCar());

            System.out.println(
                    "Car No: " +
                    r.getCarNumber());

            System.out.println(
                    "Common Time: " +
                    Matcher.commonTime(
                            wanted,
                            r));
        }
    }

    static void join()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        String ride =
                sc.nextLine();

        System.out.print(
                "Student ID: ");

        String student =
                sc.nextLine();

        data.join(
                ride,
                student);

        save();

        Ride r =
                data.findRide(ride);

        Student s =
                data.findStudent(student);

        System.out.println(
                "Ride joined.");

        System.out.println(
                "\n----- Ride Details -----");

        System.out.println(
                "Driver: " +
                r.getDriver());

        System.out.println(
                "Driver Contact: " +
                r.getDriverContact());

        System.out.println(
                "Car: " +
                r.getCar());

        System.out.println(
                "Car No: " +
                r.getCarNumber());

        System.out.println(
                "Car Type: " +
                r.getCarType());

        System.out.println(
                "Seats Left: " +
                r.getSeats());

        System.out.println(
                "\n----- Your Details -----");

        System.out.println(
                "ID: " +
                s.getId());

        System.out.println(
                "Name: " +
                s.getName());

        System.out.println(
                "Branch: " +
                s.getBranch());

        System.out.println(
                "Contact: " +
                s.getContact());

        System.out.println(
                "\n----- Ride Members -----");

        data.showPeople(ride);
    }

    static void leave()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        String ride =
                sc.nextLine();

        System.out.print(
                "Student ID: ");

        String student =
                sc.nextLine();

        data.leave(
                ride,
                student);

        save();

        System.out.println(
                "Ride left.");
    }

    static void people()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        data.showPeople(
                sc.nextLine());
    }

    static void cancel()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        String ride =
                sc.nextLine();

        System.out.print(
                "Owner ID: ");

        String owner =
                sc.nextLine();

        data.cancel(
                ride,
                owner);

        save();

        System.out.println(
                "Ride cancelled.");
    }

    static void complete()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        String ride =
                sc.nextLine();

        System.out.print(
                "Owner ID: ");

        String owner =
                sc.nextLine();

        data.complete(
                ride,
                owner);

        save();

        System.out.println(
                "Ride completed.");
    }

    static void remove()
            throws RideException {

        System.out.print(
                "Ride ID: ");

        String ride =
                sc.nextLine();

        System.out.print(
                "Owner ID: ");

        String owner =
                sc.nextLine();

        data.remove(
                ride,
                owner);

        save();

        System.out.println(
                "Ride removed.");
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
