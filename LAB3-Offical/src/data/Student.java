package data;

import java.io.Serializable;

public class Student implements Serializable{

    String studentId;
    String studentName;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", studentName=" + studentName + '}';
    }

}
