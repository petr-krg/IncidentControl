package krg.petr.naumen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity{

    @Column(name = "first_name", unique = true, length = 50)
    private String firstName;

    @Column(name = "last_name", unique = true, length = 50)
    private String lastName;

    @Column(name = "pat_name", unique = true, length = 50)
    private String patName;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Integer gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}