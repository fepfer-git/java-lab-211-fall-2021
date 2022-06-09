package tools;

import data.Injection;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import data.Vaccine;
import data.Student;
import java.util.List;

public class writeFile {

    public static void writeVaccine() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            ArrayList<Vaccine> list = new ArrayList();
            list.add(new Vaccine("Covid001", "AstraZeneca"));
            list.add(new Vaccine("Covid002", "Pfizer"));
            list.add(new Vaccine("Covid003", "Moderna"));
            list.add(new Vaccine("Covid004", "Nanocovax"));
            list.add(new Vaccine("Covid005", "SPUTNIK V"));

            outputStream.writeObject(list);
            outputStream.close();
            fileOut.close();
        } catch (Exception e) {
        }

    }

    public static void writeStudent() {
        FileOutputStream fileOut = null;
        ObjectOutputStream outputStream = null;
        try {
            String fileName = "student.dat";
            fileOut = new FileOutputStream(fileName);
            outputStream = new ObjectOutputStream(fileOut);

            ArrayList<Student> list = new ArrayList();
            list.add(new Student("SSG17001", "Thai"));
            list.add(new Student("SSG17002", "Minh"));
            list.add(new Student("SSG17003", "Tung"));
            list.add(new Student("SSG17004", "Tam"));
            list.add(new Student("SSG17005", "Linh"));
            list.add(new Student("SSG17006", "Hai"));
            list.add(new Student("SSG17007", "Thiep"));
            list.add(new Student("SSG17008", "Thy"));
            list.add(new Student("SSG17009", "Tri"));

            outputStream.writeObject(list);
            outputStream.close();
            fileOut.close();
        } catch (Exception e) {
        }

    }

    public static void writeInjection(List<Injection> data) {
        FileOutputStream fileOut = null;
        ObjectOutputStream outputStream = null;
        try {
            String fileName = "injection.dat";
            fileOut = new FileOutputStream(fileName);
            outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(data);
            System.out.println("Store injection to file injection.dat successfully!");
            outputStream.close();
            fileOut.close();
        } catch (Exception e) {
        }

    }
}
