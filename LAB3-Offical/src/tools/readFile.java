
package tools;

import data.Student;
import data.Vaccine;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class readFile {

    public static List readStudent(){
        List<Student> list = new ArrayList();
        FileInputStream fileIn = null;
        ObjectInputStream inputStream = null;
        
        try {
            fileIn = new FileInputStream("student.dat");
            inputStream = new ObjectInputStream(fileIn);
            
            list = (List<Student>) inputStream.readObject();
        } catch (Exception e) {
        }
        return list;
    }
    
    public static List readVaccine(){
        List<Vaccine> list = new ArrayList();
        FileInputStream fileIn = null;
        ObjectInputStream inputStream = null;
        
        try {
            fileIn = new FileInputStream("vaccine.dat");
            inputStream = new ObjectInputStream(fileIn);
            
            list = (List<Vaccine>) inputStream.readObject();
        } catch (Exception e) {
        }
        return list;
    }
    
    public static List readInjection(){
        List<Vaccine> list = new ArrayList();
        FileInputStream fileIn = null;
        ObjectInputStream inputStream = null;
        
        try {
            fileIn = new FileInputStream("injection.dat");
            inputStream = new ObjectInputStream(fileIn);
            
            list = (List<Vaccine>) inputStream.readObject();
        } catch (Exception e) {
        }
        return list;
    }
    
}
