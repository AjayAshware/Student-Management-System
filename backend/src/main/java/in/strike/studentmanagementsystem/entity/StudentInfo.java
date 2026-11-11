package in.strike.studentmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity   //  Hibernate ko batata hai ki class ek database entity hai.
public class StudentInfo {

    @Id  // — batata hai ki kaunsa field primary key hai.
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rollNum;
    private String email;
    private String subject;
    private int age;

    public StudentInfo(){

    }  //  empty contructor

    public StudentInfo(Long id, String name, String rollNum, String email, String subject, int age) {
        this.id = id;
        this.name = name;
        this.rollNum = rollNum;
        this.email = email;
        this.subject = subject;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
