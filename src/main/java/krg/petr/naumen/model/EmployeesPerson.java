package krg.petr.naumen.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.annotations.SQLRestriction;

@Entity
@DiscriminatorValue("Employee")
@SQLRestriction("employee = 1")
public class EmployeesPerson extends Person{

}