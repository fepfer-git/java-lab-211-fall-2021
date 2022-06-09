package data;

import java.io.Serializable;

public class Vaccine implements Serializable{

    public String vaccineId;
    public String vaccineName;

    public Vaccine(String vaccineId, String vaccineName) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return "Vaccine{" + "vaccineId=" + vaccineId + ", vaccineName=" + vaccineName + '}';
    }
    
    public void display(){
        System.out.println(toString());
    }
    
    
}
