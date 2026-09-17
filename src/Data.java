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

        for (Student s :
                data.getStudents()) {

            bw.write(
                    s.getName() + "|" +
                    s.getLocation() + "|" +
                    s.getDestination() + "|" +
                    s.getDate() + "|" +
                    s.getTime());

            bw.newLine();
        }

        bw.close();

        bw = new BufferedWriter(
                new FileWriter("data/rides.txt"));

        for (Ride r :
                data.getRides()) {

            bw.write(
                    r.getId() + "|" +
                    r.getOwner() + "|" +
                    r.getLocation() + "|" +
                    r.getDestination() + "|" +
                    r.getDate() + "|" +
                    r.getTime() + "|" +
                    r.getFare() + "|" +
                    members(r));

            bw.newLine();
        }

        bw.close();
    }

    static String members(Ride r) {

        String x = "";

        for (int i = 0;
             i < r.getMembers().size();
             i++) {

            if (i > 0)
                x += ",";

            x += r.getMembers().get(i);
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

            while ((line =
                    br.readLine()) != null) {

                String[] p =
                        line.split("\\|");

                if (p.length == 5) {

                    data.getStudents().add(
                            new Student(
                                    p[0],
                                    p[1],
                                    p[2],
                                    p[3],
                                    p[4]));
                }
            }

            br.close();

        } catch (Exception e) {
        }

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "data/rides.txt"));

            String line;

            while ((line =
                    br.readLine()) != null) {

                String[] p =
                        line.split("\\|", -1);

                if (p.length >= 8) {

                    ArrayList<String> names =
                            new ArrayList<String>();

                    if (p[7].length() > 0) {

                        String[] people =
                                p[7].split(",");

                        for (String name :
                                people)
                            names.add(name);
                    }

                    Ride r =
                            new Ride(
                                    p[0], p[1], p[2],
                                    p[3], p[4], p[5],
                                    Integer.parseInt(p[6]),
                                    names);

                    data.getRides().add(r);
                }
            }

            br.close();

        } catch (Exception e) {
        }
    }
}
