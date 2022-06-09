package data;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Injection implements Serializable {

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    String injectionId;
    Date injectionFirstDate;
    Date injectionSecondDate;
    String injectionFristPlace;
    String injectionSecondPlace;
    String studentId;
    String vaccineId;
    String studentName;

    public Injection(String injectionId, String studentName, Date injectionFirstDate, Date injectionSecondDate, String injectionFristPlace, String injectionSecondPlace, String studentId, String vaccineId) {
        this.injectionId = injectionId;
        this.injectionFirstDate = injectionFirstDate;
        this.injectionSecondDate = injectionSecondDate;
        this.injectionFristPlace = injectionFristPlace;
        this.injectionSecondPlace = injectionSecondPlace;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.studentName = studentName;
    }

    public String getInjectionId() {
        return injectionId;
    }

    public Date getInjectionFirstDate() {
        return injectionFirstDate;
    }

    public Date getInjectionSecondDate() {
        return injectionSecondDate;
    }

    public String getInjectionFristPlace() {
        return injectionFristPlace;
    }

    public String getInjectionSecondPlace() {
        return injectionSecondPlace;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public String getStudentName() {
        return studentName;
    }
    
    
    public void setInjectionFirstDate(Date injectionFirstDate) {
        this.injectionFirstDate = injectionFirstDate;
    }

    public void setInjectionSecondDate(Date injectionSecondDate) {
        this.injectionSecondDate = injectionSecondDate;
    }

    public void setInjectionFristPlace(String injectionFristPlace) {
        this.injectionFristPlace = injectionFristPlace;
    }

    public void setInjectionSecondPlace(String injectionSecondPlace) {
        this.injectionSecondPlace = injectionSecondPlace;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    
    @Override
    public String toString() {
        return "Injection{" + "injectionId=" + injectionId + ", injectionFirstDate=" + injectionFirstDate + ", injectionSecondDate=" + injectionSecondDate + ", injectionFristPlace=" + injectionFristPlace + ", injectionSecondPlace=" + injectionSecondPlace + ", studentId=" + studentId + ", vaccineId=" + vaccineId + '}';
    }

    public void Display() {
        if (injectionSecondDate == null) {
            System.out.printf("|%-11s|%-9s|%-9s|%-17s|%-17s|%-16s|%-16s|\n",
                    injectionId, studentId, vaccineId, injectionFristPlace,
                    injectionSecondPlace, df.format(injectionFirstDate), injectionSecondDate);
        } else {
            System.out.printf("|%-11s|%-9s|%-9s|%-17s|%-17s|%-16s|%-16s|\n",
                    injectionId, studentId, vaccineId, injectionFristPlace,
                    injectionSecondPlace, df.format(injectionFirstDate), df.format(injectionSecondDate));
        }
    }
    
}
