import java.io.*;
import java.util.ArrayList;

public class Data {

    public static void save(Rides data)
            throws IOException {

        new File("data").mkdirs();

        BufferedWriter bw =
                new BufferedWriter(
                        new FileWriter(
                                "data/students.txt"));

        for (Student s : data.getStudents()) {
            bw.write(
                    s.getId() + "|" +
                    s.getName() + "|" +
                    s.getBranch() + "|" +
                    s.getContact()
            );
            bw.newLine();
        }

        bw.close();

        bw = new BufferedWriter(
                new FileWriter("data/rides.txt"));

        for (Ride r : data.getRides()) {
            bw.write(
                    r.getId() + "|" +
                    r.getOwner() + "|" +
                    r.getPickup() + "|" +
                    r.getDestination() + "|" +
                    r.getDate() + "|" +
                    r.getStart() + "|" +
                    r.getEnd() + "|" +
                    r.getDriver() + "|" +
                    r.getDriverContact() + "|" +
                    r.getCar() + "|" +
                    r.getCarNumber() + "|" +
                    r.getCarType() + "|" +
                    r.getTotalSeats() + "|" +
                    r.getSeats() + "|" +
                    r.getStatus() + "|" +
                    people(r)
            );
            bw.newLine();
        }

        bw.close();
    }

    static String people(Ride r) {
        String x = "";

        for (int i = 0;
             i < r.getPeople().size();
             i++) {

            if (i > 0)
                x += ",";

            x += r.getPeople().get(i);
        }

        return x;
    }

    public static void load(Rides data) {

        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "data/students.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] p =
                        line.split("\\|");

                if (p.length == 4) {
                    data.getStudents().add(
                            new Student(
                                    p[0],
                                    p[1],
                                    p[2],
                                    p[3]
                            )
                    );
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println(
                    "No student data found.");
        }

        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "data/rides.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] p =
                        line.split("\\|", -1);

                if (p.length >= 16) {

                    ArrayList<String> people =
                            new ArrayList<String>();

                    if (p[15].length() > 0) {

                        String[] ids =
                                p[15].split(",");

                        for (String id : ids)
                            people.add(id);
                    }

                    Ride r =
                            new Ride(
                                    p[0], p[1], p[2], p[3],
                                    p[4], p[5], p[6], p[7],
                                    p[8], p[9], p[10], p[11],
                                    Integer.parseInt(p[12]),
                                    Integer.parseInt(p[13]),
                                    p[14], people
                            );

                    data.getRides().add(r);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println(
                    "No ride data found.");
        }
    }
}
