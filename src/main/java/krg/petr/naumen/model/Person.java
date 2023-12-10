package krg.petr.naumen.model;


import jakarta.persistence.*;
import krg.petr.naumen.model.enumeration.Gender;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

import static java.lang.String.format;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case when employee = 1 " +
                      "then 'Employee' else ( " +
                      "    case when employee = 0 "+
                      "    then 'Client' else 'Unknown' end) " +
                      "end ")
public class Person extends BaseEntity{
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "pat_name", length = 50)
    private String patName;

    @Column(name = "phone", unique = true, length = 20)
    private String phone;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "employee")
    private Integer employee;

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGenderDisplayName() {
        Gender userGender = Gender.fromCode(gender);
        return (gender != null ? userGender.getDisplayName() : "");
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFIO() {
        return format("%s %s %s", lastName, firstName, patName);
    }
}