import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static RideManager manager = new RideManager();

    public static void main(String[] args) {

        loadData();

        while (true) {

            System.out.println();
            System.out.println("========== VITRIDE ==========");
            System.out.println("1.  Add Student");
            System.out.println("2.  View Students");
            System.out.println("3.  Create Ride");
            System.out.println("4.  View Rides");
            System.out.println("5.  Find Ride Match");
            System.out.println("6.  Join Ride");
            System.out.println("7.  Leave Ride");
            System.out.println("8.  View Passengers");
            System.out.println("9.  Cancel Ride");
            System.out.println("10. Complete Ride");
            System.out.println("11. Remove Ride");
            System.out.println("12. Save Data");
            System.out.println("13. Exit");
            System.out.println("==============================");
            System.out.print("Choice: ");

            try {

                int choice =
                        Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewStudents();
                        break;

                    case 3:
                        createRide();
                        break;

                    case 4:
                        viewRides();
                        break;

                    case 5:
                        findMatch();
                        break;

                    case 6:
                        joinRide();
                        break;

                    case 7:
                        leaveRide();
                        break;

                    case 8:
                        viewPassengers();
                        break;

                    case 9:
                        cancelRide();
                        break;

                    case 10:
                        completeRide();
                        break;

                    case 11:
                        removeRide();
                        break;

                    case 12:
                        saveData();
                        break;

                    case 13:
                        saveData();
                        System.out.println("Goodbye.");
                        return;

                    default:
                        System.out.println(
                                "Invalid choice."
                        );
                }

            } catch (NumberFormatException |
                     DateTimeParseException e) {

                System.out.println(
                        "Enter a valid value."
                );

            } catch (InvalidRideException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    static void addStudent()
            throws InvalidRideException {

        System.out.print("Student ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Branch: ");
        String branch = sc.nextLine().trim();

        manager.addStudent(
                new Student(id, name, branch)
        );

        saveData();

        System.out.println("Student added.");
    }

    static void viewStudents() {

        if (manager.getStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student :
                manager.getStudents().values()) {

            System.out.println(student);
        }
    }

    static void createRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Owner Student ID: ");
        String ownerId = sc.nextLine().trim();

        System.out.print("Pickup: ");
        String pickup = sc.nextLine().trim();

        System.out.print("Destination: ");
        String destination = sc.nextLine().trim();

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date =
                LocalDate.parse(sc.nextLine().trim());

        System.out.print("Start Time (HH:MM): ");
        LocalTime start =
                LocalTime.parse(sc.nextLine().trim());

        System.out.print("End Time (HH:MM): ");
        LocalTime end =
                LocalTime.parse(sc.nextLine().trim());

        System.out.print("Available Passenger Seats: ");
        int seats =
                Integer.parseInt(sc.nextLine().trim());

        manager.addRide(
                new Ride(
                        id,
                        ownerId,
                        pickup,
                        destination,
                        date,
                        start,
                        end,
                        seats
                )
        );

        saveData();

        System.out.println("Ride created.");
    }

    static void viewRides() {

        if (manager.getRides().isEmpty()) {
            System.out.println("No rides found.");
            return;
        }

        for (Ride ride : manager.getRides()) {
            System.out.println(ride);
        }
    }

    static void findMatch()
            throws InvalidRideException {

        System.out.print("Your Student ID: ");
        String studentId = sc.nextLine().trim();

        if (manager.findStudent(studentId) == null) {
            throw new InvalidRideException(
                    "Student not found."
            );
        }

        System.out.print("Pickup: ");
        String pickup = sc.nextLine().trim();

        System.out.print("Destination: ");
        String destination = sc.nextLine().trim();

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date =
                LocalDate.parse(sc.nextLine().trim());

        System.out.print("Your Start Time (HH:MM): ");
        LocalTime start =
                LocalTime.parse(sc.nextLine().trim());

        System.out.print("Your End Time (HH:MM): ");
        LocalTime end =
                LocalTime.parse(sc.nextLine().trim());

        Ride searchRide =
                new Ride(
                        "SEARCH",
                        studentId,
                        pickup,
                        destination,
                        date,
                        start,
                        end,
                        1
                );

        List<Ride> matches =
                manager.findMatches(
                        studentId,
                        date,
                        pickup,
                        destination,
                        searchRide
                );

        if (matches.isEmpty()) {
            System.out.println(
                    "No compatible ride found."
            );
            return;
        }

        System.out.println("\nCompatible rides:");

        for (Ride ride : matches) {

            System.out.println();
            System.out.println(
                    "Ride ID     : " + ride.getId()
            );
            System.out.println(
                    "Owner       : " + ride.getOwnerId()
            );
            System.out.println(
                    "Route       : " + ride.getPickup()
                            + " -> " + ride.getDestination()
            );
            System.out.println(
                    "Date        : " + ride.getDate()
            );
            System.out.println(
                    "Ride Time   : " + ride.getStartTime()
                            + " - " + ride.getEndTime()
            );
            System.out.println(
                    "Common Time : " +
                            RideMatcher.commonTime(
                                    searchRide, ride
                            )
            );
            System.out.println(
                    "Seats Left  : " + ride.getSeats()
            );
        }
    }

    static void joinRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        System.out.print("Your Student ID: ");
        String studentId = sc.nextLine().trim();

        manager.joinRide(rideId, studentId);

        saveData();

        System.out.println("Ride joined.");
    }

    static void leaveRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        System.out.print("Your Student ID: ");
        String studentId = sc.nextLine().trim();

        manager.leaveRide(rideId, studentId);

        saveData();

        System.out.println("Ride left.");
    }

    static void viewPassengers()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        manager.showPassengers(rideId);
    }

    static void cancelRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        System.out.print("Your Student ID (owner): ");
        String studentId = sc.nextLine().trim();

        manager.cancelRide(rideId, studentId);

        saveData();

        System.out.println("Ride cancelled.");
    }

    static void completeRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        System.out.print("Your Student ID (owner): ");
        String studentId = sc.nextLine().trim();

        manager.completeRide(rideId, studentId);

        saveData();

        System.out.println("Ride completed.");
    }

    static void removeRide()
            throws InvalidRideException {

        System.out.print("Ride ID: ");
        String rideId = sc.nextLine().trim();

        System.out.print("Your Student ID (owner): ");
        String studentId = sc.nextLine().trim();

        manager.removeRide(rideId, studentId);

        saveData();

        System.out.println("Ride removed.");
    }

    static void saveData() {

        try {
            FileManager.save(manager);
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("Unable to save data.");
        }
    }

    static void loadData() {

        try {
            FileManager.load(manager);
        } catch (Exception e) {
            System.out.println(
                    "Starting with empty data."
            );
        }
    }
}
