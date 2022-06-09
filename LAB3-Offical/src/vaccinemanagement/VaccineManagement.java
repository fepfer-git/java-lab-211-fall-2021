package vaccinemanagement;

import data.StudentInjectionInformation;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.Input;
import tools.writeFile;
import java.util.Scanner;

public class VaccineManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentInjectionInformation injList = new StudentInjectionInformation();
        writeFile.writeVaccine();
        writeFile.writeStudent();
        injList.readStuVac();
        while (true) {
            switch (runMenu()) {
                case 0:
                    injList.addData();
                    writeFile.writeInjection(injList.getInjectionList());
                    break;
                case 1:
                    injList.printInjectionList();
                    break;

                case 2:
                    String checkAdd;
                    while (true) {
                        injList.addInjection();
                        System.out.println("Press '1' to continue add or press any key to go back to the menu");
                        checkAdd = sc.nextLine().trim();
                        if (!checkAdd.equalsIgnoreCase("1")) {
                            break;
                        }
                    }
                    writeFile.writeInjection(injList.getInjectionList());
                    break;

                case 3:
                    injList.updateInjection();
                    writeFile.writeInjection(injList.getInjectionList());
                    break;

                case 4:
                    injList.deleteInjection();
                    writeFile.writeInjection(injList.getInjectionList());
                    break;
                case 5:
                    injList.searchStudent();                
                    break;
                case 6:
                    writeFile.writeInjection(injList.getInjectionList());
                    break;               
                case 9:
                    injList.readInjection();
                    System.out.println("Successfuly read injection.dat file.");
                    break;
                case 7:
                    String name;
                    name = Input.inputAStringCannotBeEmpty("Please input key word name you want to search: ", "Name is required!");
                    injList.searchStudentByKeyword(name);
                    break;
                case 8:
                    String srcText = injList.getInjectionList().toString();

                     {
                        try {
                            String enrText = injList.encrypt(srcText);
                            System.out.println("Source Text: " + srcText);
                            System.out.println("Encrypt Text: " + enrText);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(VaccineManagement.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(VaccineManagement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;

                case 10:
                    String keyword = Input.inputAString("innput keyword");
                    injList.searchPlaceByKeyword(keyword);
                    break;
                    
                default:
                    System.out.println("Choice doesn't exist, please try again!");
                    
                
            }
        }

    }

    public static int runMenu() {
        int choice;
        System.out.println("");
        System.out.println("|---------------------------------MENU------------------------------------|");
        System.out.println("|Please choose an option.                                                 |");
        System.out.println("|Press '1' to show information all students have been injected.           |");
        System.out.println("|Press '2' to add student's vaccine injection information                 |");
        System.out.println("|Press '3' to updating information of students' vaccine injection         |");
        System.out.println("|Press '4' to delete student vaccine injection information                |");
        System.out.println("|Press '5' to search for injection information by studentID               |");
        System.out.println("|Press '6' to store data in collection to injection.dat file and exit.    |");
        System.out.println("|Press '7' to search by keyword                                           |");
        System.out.println("|Press '8' to encrypt                                                     |");
        System.out.println("|-------------------------------------------------------------------------|");

        choice = Input.inputAnInteger("Please input your choice: ", "Invalid number, please try again!!!");
        return choice;
    }

}
