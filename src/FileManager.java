import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {

    private static final Path DATA =
            Paths.get("data");

    private static final Path STUDENTS =
            DATA.resolve("students.txt");

    private static final Path RIDES =
            DATA.resolve("rides.txt");

    public static void save(RideManager manager)
            throws IOException {

        Files.createDirectories(DATA);

        ArrayList<String> studentLines =
                new ArrayList<>();

        for (Student student :
                manager.getStudents().values()) {

            String name =
                    student.getName().replace("|", " ");

            String branch =
                    student.getBranch().replace("|", " ");

            studentLines.add(
                    student.getId() + "|" +
                    name + "|" +
                    branch
            );
        }

        Files.write(STUDENTS, studentLines);

        ArrayList<String> rideLines =
                new ArrayList<>();

        for (Ride ride : manager.getRides()) {

            String passengers =
                    String.join(",", ride.getPassengers());

            rideLines.add(
                    ride.getId() + "|" +
                    ride.getOwnerId() + "|" +
                    ride.getPickup() + "|" +
                    ride.getDestination() + "|" +
                    ride.getDate() + "|" +
                    ride.getStartTime() + "|" +
                    ride.getEndTime() + "|" +
                    ride.getSeats() + "|" +
                    ride.getStatus() + "|" +
                    passengers
            );
        }

        Files.write(RIDES, rideLines);
    }

    public static void load(RideManager manager)
            throws IOException {

        if (Files.exists(STUDENTS)) {

            for (String line :
                    Files.readAllLines(STUDENTS)) {

                if (line.isBlank()) {
                    continue;
                }

                String[] p =
                        line.split("\\|", -1);

                if (p.length == 3) {

                    try {
                        manager.addStudent(
                                new Student(
                                        p[0],
                                        p[1],
                                        p[2]
                                )
                        );
                    } catch (InvalidRideException e) {
                        // skip duplicate on reload
                    }
                }
            }
        }

        if (Files.exists(RIDES)) {

            for (String line :
                    Files.readAllLines(RIDES)) {

                if (line.isBlank()) {
                    continue;
                }

                String[] p =
                        line.split("\\|", -1);

                if (p.length < 10) {
                    continue;
                }

                ArrayList<String> passengers =
                        new ArrayList<>();

                if (!p[9].isBlank()) {
                    for (String id : p[9].split(",")) {
                        passengers.add(id);
                    }
                }

                Ride ride =
                        new Ride(
                                p[0],
                                p[1],
                                p[2],
                                p[3],
                                LocalDate.parse(p[4]),
                                LocalTime.parse(p[5]),
                                LocalTime.parse(p[6]),
                                Integer.parseInt(p[7]),
                                p[8],
                                passengers
                        );

                manager.getRides().add(ride);
            }
        }
    }
}
