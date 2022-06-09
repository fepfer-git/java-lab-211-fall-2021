package data;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import tools.Input;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.readFile;

public class StudentInjectionInformation {

    List<Injection> injectionList = new ArrayList();
    List<Student> studentList = new ArrayList();
    List<Vaccine> vaccineList = new ArrayList();

    public void readStuVac() {
        vaccineList = readFile.readVaccine();
        studentList = readFile.readStudent();
    }

    public void readInjection() {
        injectionList = readFile.readInjection();
    }

    public void addData() {
        Date date1 = Input.getDate("26-08-2021");
        Date date2 = Input.getDate("25-09-2021");
        Date date3 = Input.getDate("25-09-2021");
        Date date4 = null;
        Date date5 = Input.getDate("26-07-2021");
        Date date6 = Input.getDate("26-06-2021");
        injectionList.add(new Injection("ID001", "Thai", date1, date2, "Thu Duc", "Binh Tan", "SSG17001", "Covid001"));
        injectionList.add(new Injection("ID002", "Minh", date3, date4, "DH FPT", "", "SSG17002", "Covid002"));
        injectionList.add(new Injection("ID003", "Tung", date6, date5, "DH FPT", "DH FPT", "SSG17003", "Covid002"));
        System.out.println("Succesful add 3 student!");

    }

    public void addInjection() {

        String injectionId, injectionFristPlace, injectionSecondPlace, studentId, vaccineId, studentName;
        Date injectionSecondDate, injectionFirstDate;

        while (true) {
            injectionId = Input.inputAStringCannotBeEmpty("Please input injection's id: ", "Id is required");
            if (checkInjectionId(injectionId) >= 0) {
                System.out.println("The id is already exist!");
            } else {
                break;
            }
        }

        while (true) {
            studentId = Input.inputAStringCannotBeEmpty("Please input student's id: ", "Place is required");
            if (checkStudentListId(studentId) < 0) {
                System.out.println("Student not on the list!");
            } else if (checkStudentId(studentId) >= 0) {
                System.out.println("The student are already vaccinated, input a different student!");
            } else {
                int i = checkStudentListId(studentId);
                studentName = studentList.get(i).getStudentName();
                break;
            }
        }
        while (true) {
            vaccineId = Input.inputAStringCannotBeEmpty("Please input vaccineId: ", "Id is required");
            if (checkVaccineListId(vaccineId) < 0) {
                System.out.println("Vaccine not on the list!");
                System.out.println("Please input a different vaccine!");
            } else {
                break;
            }
        }

        injectionFristPlace = Input.inputAStringCannotBeEmpty("Please input 1st place: ", "Place is required");
        injectionSecondPlace = Input.inputAString("Please input 2nd place: ");
        injectionFirstDate = Input.inputDateCannotBeEmpty("Please input 1st day with the following format: (dd-MM-yyyy)", "Invalid day, please try again");
        if (injectionSecondPlace.length() == 0) {
            injectionSecondDate = Input.inputDate("Please input 2nd day with the following format: (dd-MM-yyyy)", "Invalid day, please try again");
            if (injectionSecondDate != null) {
                System.out.println("You can't input data to 2nd date while 2nd place is empty, 2nd date will be return to null!");
                injectionSecondDate = null;
            }
        } else {
            while (true) {
                injectionSecondDate = Input.inputDateCannotBeEmpty("Please input 2nd day with the following format: (dd-MM-yyyy)", "Invalid day, please try again");
                if (injectionFirstDate.getTime() > injectionSecondDate.getTime()) {
                    System.out.println("2nd date must be after 1st date");
                } else if (Input.dayBetweenTwoDate(injectionFirstDate, injectionSecondDate) < 28 || Input.dayBetweenTwoDate(injectionFirstDate, injectionSecondDate) > 84) {
                    System.out.println("Two dose must be given 4 to 12 weeks after the first injection!");
                } else {
                    break;
                }
            }

        }

        injectionList.add(
                new Injection(injectionId, studentName, injectionFirstDate, injectionSecondDate,
                        injectionFristPlace, injectionSecondPlace, studentId, vaccineId));
    }

    public void updateInjection() {
        String injectionId, injectionFirstPlace, injectionSecondPlace;
        Date injectionFirstDate, injectionSecondDate;
        int i;
        while (true) {
            injectionId = Input.inputAStringCannotBeEmpty("Please input injection's id: ", "Id is required");
            if (checkInjectionId(injectionId) < 0) {
                System.out.println("The id doesn't exist!");
                System.out.println("Please try another ID!");
            } else {
                i = checkInjectionId(injectionId);
                break;
            }
        }

        if (injectionList.get(i).getInjectionSecondDate() == null && injectionList.get(i).getInjectionSecondPlace().length() == 0) {

            injectionFirstPlace = Input.inputAString("Please update 1st place, let blank if you don't want to update.");
            if (injectionFirstPlace.length() != 0) {
                injectionList.get(i).setInjectionFristPlace(injectionFirstPlace);
                System.out.println("1st Place update Successfully!");
            }

            injectionFirstDate = Input.inputDate("Please update 1st day with the following format: (dd-MM-yyyy), let blank if you don't want to update.", "Invalid day, please try again");
            if (injectionFirstDate != null) {
                injectionList.get(i).setInjectionFirstDate(injectionFirstDate);
                System.out.println("1st Date update Successfully!");
            }

            System.out.println("This student didn't finished the second injection yet, do you want to add the second injection?");
            System.out.println("Press '1' to add, press '0' to return to the menu.");
            if (Input.inputChoice() == 1) {
                injectionSecondPlace = Input.inputAString("Please update 2nd place, let blank if you don't want to update.");
                if (injectionSecondPlace.length() != 0) {
                    injectionList.get(i).setInjectionSecondPlace(injectionSecondPlace);
                    System.out.println("2nd Place update Successfully!");
                    while (true) {
                        injectionSecondDate = Input.inputDate("Please update 2nd date with the following format: (dd-MM-yyyy)", "Invalid day, please try again");
                        if (injectionSecondDate != null) {
                            if (Input.compareDate(injectionList.get(i).injectionFirstDate, injectionSecondDate) != 1) {
                                System.out.println("2nd date must be after 1s1 date");
                            } else if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), injectionSecondDate) < 28 || Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), injectionSecondDate) > 84) {
                                System.out.println("Two dose must be given 4 to 12 weeks after the first injection!");
                            } else {
                                injectionList.get(i).injectionSecondDate = injectionSecondDate;
                                System.out.println("Date update Successfully!");
                                break;
                            }
                        } else {
                            System.out.println("Date is required!");
                        }

                    }
                    System.out.println("Student has completed 2 injections!");
                }

            }

        } else {
            injectionFirstPlace = Input.inputAString("Please update 1st place, let blank if you don't want to update.");
            if (injectionFirstPlace.length() != 0) {
                injectionList.get(i).setInjectionFristPlace(injectionFirstPlace);
                System.out.println("1st Place update Successfully!");
            }

            while (true) {
                injectionFirstDate = Input.inputDate("Please update 1st day with the following format: (dd-MM-yyyy), let blank if you don't want to update.", "Invalid day, please try again");
                if (injectionFirstDate != null) {
                    if (Input.compareDate(injectionFirstDate, injectionList.get(i).getInjectionSecondDate()) != 1) {
                        System.out.println("1st date must be before 2nd date");
                    } else if (Input.dayBetweenTwoDate(injectionFirstDate, injectionList.get(i).getInjectionSecondDate()) < 28 || Input.dayBetweenTwoDate(injectionFirstDate, injectionList.get(i).getInjectionSecondDate()) > 84) {
                        System.out.println("Two dose must be given 4 to 12 weeks after the first injection!");
                    } else {
                        injectionList.get(i).setInjectionFirstDate(injectionFirstDate);
                        System.out.println("1st Date update Successfully!");
                        break;
                    }

                } else {
                    break;
                }

            }

            injectionSecondPlace = Input.inputAString("Please update 2nd place, let blank if you don't want to update.");
            if (injectionSecondPlace.length() != 0) {
                injectionList.get(i).setInjectionSecondPlace(injectionSecondPlace);
                System.out.println("2nd Place update Successfully!");
            }

            while (true) {
                injectionSecondDate = Input.inputDate("Please update 2nd date with the following format: (dd-MM-yyyy), let blank if you don't want to update.", "Invalid day, please try again");
                if (injectionSecondDate != null) {
                    if (Input.compareDate(injectionList.get(i).injectionFirstDate, injectionSecondDate) != 1) {
                        System.out.println("2st date must be after 1nd date");
                    } else if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), injectionSecondDate) < 28 || Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), injectionSecondDate) > 84) {
                        System.out.println("Two dose must be given 4 to 12 weeks after the first injection!");
                    } else {
                        injectionList.get(i).injectionSecondDate = injectionSecondDate;
                        System.out.println("Date update Successfully!");
                        break;
                    }
                } else {
                    break;
                }

            }
        }

        System.out.println("");
        System.out.println("Student information after update: ");
        injectionList.get(i).Display();

    }

    public void deleteInjection() {
        String id;
        int pos, choice;
        id = Input.inputAStringCannotBeEmpty("Input the injectionID you want to remove!", "ID is required!");
        pos = checkInjectionId(id);
        System.out.println("_______________________________________________________________________");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else if (pos == -2) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("Are you sure you want to remove this injection?");
            injectionList.get(pos).Display();
            System.out.println("Press 1 to remove the injection above.");
            System.out.println("Press 2 to back to the menu.");
            while (true) {
                choice = Input.inputAnInteger("Please input your choice!", "Invalid choice, please try again!!!");
                if (choice == 1) {
                    injectionList.remove(pos);
                    System.out.println("The selected injection is removed successfully!");
                    break;
                } else if (choice == 2) {
                    System.out.println("Delete fail!");
                    break;
                } else {
                    System.out.println("Invalid choice, please try again!!!");
                }
            }

        }
    }

    public void searchStudent() {
        String id;
        int i;
        id = Input.inputAStringCannotBeEmpty("Please input student's ID you want to search!", "Id is required!");
        if (checkStudentId(id) < 0) {
            System.out.println("Not found!");
        } else {
            i = checkStudentId(id);
            System.out.println("The injection information of student you want to find: ");
            injectionList.get(i).Display();
        }
    }

    public void searchStudentByKeyword(String keyWord) {
        String studentName;
        if (injectionList.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println("|InjectionID|StudentID|VaccineID|Injection1stPlace|Injection2ndPlace|Injection1stDate|Injection2ndDate|");
            for (int i = 0; i < injectionList.size(); i++) {
                keyWord = keyWord.toLowerCase();
                studentName = injectionList.get(i).getStudentName().toLowerCase();
                if (studentName.contains(keyWord)) {
                    injectionList.get(i).Display();
                }

            }
        }
    }

    public void searchPlaceByKeyword(String keyWord) {
        String place1, place2;
        if (injectionList.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println("|InjectionID|StudentID|VaccineID|Injection1stPlace|Injection2ndPlace|Injection1stDate|Injection2ndDate|");
            for (int i = 0; i < injectionList.size(); i++) {
                keyWord = keyWord.toLowerCase();
                place1 = injectionList.get(i).getInjectionFristPlace().toLowerCase();
                place2 = injectionList.get(i).getInjectionSecondPlace().toLowerCase();
                if (place1.contains(keyWord)) {
                    injectionList.get(i).Display();
                }else if(place2.contains(keyWord)){
                    injectionList.get(i).Display();
                }

            }
        }
    }

    public void searchDate() {
        int i = 0;
        int pos = 0;
        pos = checkDate(i);
        if (pos == -1) {
            System.out.println("empty");
        } else {
            while (pos != -1) {
                pos = checkDate(i);
                if (pos != -1) {
                    if (pos != -2) {
                        injectionList.get(pos).Display();
                    }
                }
                i++;
            }

        }
    }

    public int checkDate(int i) {
        Date date;
        date = Input.getDate("25-09-2021");
        if (injectionList.isEmpty()) {
            return -1;
        }

        for (; i < injectionList.size(); i++) {
            if (injectionList.get(i).getInjectionSecondDate() != null) {
                if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), date) == 0 || Input.dayBetweenTwoDate(injectionList.get(i).getInjectionSecondDate(), date) == 0) {
                    return i;
                }
                return -2;
            } else {
                if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), date) == 0) {
                    return i;
                }
                return -2;
            }

        }

        return -1;
    }

    public void printInjectionList() {
        if (injectionList.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println("|InjectionID|StudentID|VaccineID|Injection1stPlace|Injection2ndPlace|Injection1stDate|Injection2ndDate|");
            for (int i = 0; i < injectionList.size(); i++) {
                injectionList.get(i).Display();
            }
        }
    }

    public void printByDate() {
        Date date = Input.getDate("25-09-2021");
        if (injectionList.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println("|InjectionID|StudentID|VaccineID|Injection1stPlace|Injection2ndPlace|Injection1stDate|Injection2ndDate|");
            for (int i = 0; i < injectionList.size(); i++) {
                if (injectionList.get(i).getInjectionSecondDate() != null) {
                    if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), date) == 0 || Input.dayBetweenTwoDate(injectionList.get(i).getInjectionSecondDate(), date) == 0) {
                        injectionList.get(i).Display();
                    }
                } else if (Input.dayBetweenTwoDate(injectionList.get(i).getInjectionFirstDate(), date) == 0) {
                    injectionList.get(i).Display();
                }

            }
        }
    }

    public int checkInjectionId(String Id) {
        if (injectionList.isEmpty()) {
            return -2;
        }

        for (int i = 0; i < injectionList.size(); i++) {
            if (injectionList.get(i).getInjectionId().equalsIgnoreCase(Id)) {
                return i;
            }
        }

        return -1;
    }

    public int checkStudentId(String Id) {
        if (injectionList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < injectionList.size(); i++) {
            if (injectionList.get(i).getStudentId().equalsIgnoreCase(Id)) {
                return i;
            }
        }

        return -1;
    }

    public int checkVaccineListId(String vacId) {
        if (vaccineList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).getVaccineId().equalsIgnoreCase(vacId)) {
                return i;
            }
        }

        return -1;
    }

    public int checkStudentListId(String vacId) {
        if (studentList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equalsIgnoreCase(vacId)) {
                return i;
            }
        }

        return -1;
    }

    public int getInjectionIDIndex(String injectionId) {
        if (injectionList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < injectionList.size(); i++) {
            if (injectionList.get(i).getInjectionId().equalsIgnoreCase(injectionId)) {
                return i;
            }
        }
        return -1;
    }

    public String encrypt(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String enrText;

        MessageDigest msd = MessageDigest.getInstance("MD5");
        byte[] srcTextBytes = srcText.getBytes("UTF-8");
        byte[] enrTextBytes = msd.digest(srcTextBytes);

        BigInteger bigInt = new BigInteger(1, enrTextBytes);
        enrText = bigInt.toString(16);

        return enrText;
    }

    public List<Injection> getInjectionList() {
        return injectionList;
    }
}
